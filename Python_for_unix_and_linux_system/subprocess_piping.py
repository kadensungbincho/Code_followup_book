#!/usr/bin/env python

import subprocess


p = subprocess.Popen("df -h", shell=True, stdout=subprocess.PIPE)

out = p. stdout.readlines()

# for line in out:
#     print(line.strip())


def multi(*args):
    for cmd in args:
        p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE)
        out = p.stdout.read()
        print(out)

multi("df -h", "ls -l /tmp", "tail /var/log/system.log")
