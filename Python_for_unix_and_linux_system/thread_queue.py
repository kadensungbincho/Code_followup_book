#!/usr/bin/env python
# This requires Python 2.5 or greater

from threading import Thread
import subprocess
from queue import Queue
import re

num_ping_threads = 3
num_arp_threads = 3
in_queue = Queue()
out_queue = Queue()
ips = ["kadensungbincho.com", "google.com", "facebook.com", "naver.com"]

def pinger(i, iq, oq):
    """Pings subnet"""
    while True:
        ip = iq.get()
        print("Thread {}: Pingin {}".format(i, ip))
        ret = subprocess.call("ping -c 1 {}".format(ip),
                            shell=True,
                            stdout=open('null', 'w'),
                            stderr=subprocess.STDOUT
                            )
        if ret == 0:
            print("{}: is alive".format(ip))
            oq.put(ip)
        else:
            print("{}: did not respond".format(ip))

        iq.task_done()

def arping(i, oq):
    """grabs a valid IP address from a queue and gets macaddr"""
    while True:
        ip = oq.get()
        p = subprocess.Popen("arping -c 1 {}".format(ip),
                                shell=True,
                                stdout=subprocess.PIPE
                                )
        out = p.stdout.read()

        # Match and extract mac address from stdout
        result = out.split()
        pattern = re.compile(":")
        macaddr = None
        for item in result:
            if re.search(pattern, item):
                macaddr = item
        print("IP Address: {} | Mac Address: {}".format(ip, macaddr))
        oq.task_done()


# Place ip addresses into in queue
for ip in ips:
    in_queue.put(ip)


# Spawn pool of ping threads
for i in range(num_ping_threads):
    worker = Thread(target=pinger, args=(i, in_queue, out_queue))
    worker.setDaemon(True)
    worker.start()


# Spawn pool of arping threads
for i in range(num_arp_threads):
    worker = Thread(target=arping, args=(i, out_queue))
    worker.setDaemon(True)
    worker.start()


print("Main Thread Waiting")
# Ensures that program does not exit until both queues have been emptied
in_queue.join()
out_queue.join()

print("Done")


