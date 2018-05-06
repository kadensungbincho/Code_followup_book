#!/usr/bin/env python
from subprocess import call
import time
import sys

""" Subtube is module that simplifies and automates some aspects of subprocess"""

class BaseArgs(object):
    """Base Argument Class that handles keyword argument parsing"""
    def __init__(self, *args, **kwargs):
        self.args = args
        self.kwargs = kwargs
        if "delay" in self.kwargs.keys():
            self.delay = self.kwargs["delay"]
        else:
            self.delay = 0

        if "verbose" in self.kwargs.keys():
            self.verbose = self.kwargs["verbose"]
        else:
            self.verbose = False

    def run(self):
        """You must implement a run method"""
        raise NotImplementedError


class Runner(BaseArgs):
    """
    Simplifies subprocess call and runs call over a sequence of commands

    Runner takes N positional arguments, and optionally:

    [optional keyword parameters]
    delay=1, for time delay in seconds
    verbose=True, for verbose output

    Usage:
    
    cmd = Runner("ls -l", "df -h", verbose=True, delay=3)
    cmd.run()

    """

    def run(self):
        for cmd in self.args:
            if self.verbose:
                print("Running {} with delay={}".format(cmd, self.delay))
            time.sleep(self.delay)
            call(cmd, shell=True)


if __name__ == "__main__":
    r = Runner("df -h", "du -h /tmp")
    r.run()

    import pwd
    print(pwd.getpwnam('root'))
