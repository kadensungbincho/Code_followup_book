#subtly bad design because of shared state

import threading
import time


count = 1
class KissThread(threading.Thread):
    def run(self):
        global count
        print("Thread # {}: Pretending to do stuff".format(count))
        count += 1
        time.sleep(2)
        print("done with stuff")


# for t in range(5):
#    KissThread().start()


############################# 2nd
from threading import Thread
import subprocess
from Queue import Queue

num_threads = 3
queue = Queue()
ips = ["10.0.1.1", "10.0.1.3", "10.0.1.11", "10.0.1.51"]

def pinger(i, q):
    """Pings subnet"""
    while True:
        ip = q.get()
        print("Thread {}: Pinging {}".format(i, ip))
        ret = subprocess.call("ping -c 1 {}".format(ip), 
                            shell=True,
                            stdout=open('/dev/null', 'w'),
                            stderr=subprocess.STDOUT
                            )
        if ret == 0:
            print("{}: is alive".format(ip))
        else:
            print("{}: did not respond".format(ip))
        q.task_done()

for i in range(num_threads):
    worker = Thread(target=pinger, args=(i, queue))
    worker.setDaemon(True)
    worker.start()


for ip in ips:
    queue.put(ip)


print("Main Thread Waiting")
# By commenting out the join, the main program exits before thread have a chance to run
queue.join()
print("Done")
