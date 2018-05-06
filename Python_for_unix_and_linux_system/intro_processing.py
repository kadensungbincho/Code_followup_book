#!/usr/bin/env python

from processing import Process, Queue
import time

def f(q):
    x = q.get()
    print("Process number {}, sleeps for {} seconds".format(x, x))
    time.sleep(x)
    print("Process number {} finished".format(x))

q = Queue()


for i in range(10):
    q.put(i)
    i = Process(target=f, args=[q])
    i.start()


print("Main Process joins on queue")
i.join()
print("Main Program finished")

