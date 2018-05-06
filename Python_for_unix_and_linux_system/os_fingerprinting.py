#!/usr/bin/env python
import platform

"""
Fingerprints the following Operating Systems:

    * Mac OS X
    * Ubuntu
    * Red Hat/Cent OS
    * FreeBSD
    * SunOS

"""

class OpSysType(object):
    """Determines OS Type using platform module"""

    def __getattr__(self, attr):
        if attr == "osx":
            return "osx"
        elif attr == "rhel":
            return "redhat"
        elif attr == "ubu":
            return "ubuntu"
        elif attr == "fbsd":
            return "FreeBSD"
        elif attr == "sun":
            return "SunOS"
        elif attr == "unknown_linux":
            return "unknown_linux"
        elif attr == "unknown":
            return "unknown"
        else:
            raise AttributeError(attr)

    def linux_type(self):
        """Uses various methods to determine Linux Type"""
        if platform.dist()[0] == self.rhel:
            return self.rhel
        elif platform.uname()[1] == self.ubu:
            return self.ubu
        else:
            return self.unknown_linux

    def query_os(self):
        if platform.system() == "Darwin":
            return self.osx
        elif platform.system() == "Linux":
            return self.linux_type()
        elif platform.system() == self.sun:
            return self.sun
        elif platform.system() == self.fbsd:
            return self.fbsd
    

def fingerprint():
    type_ = OpSysType()
    print(type_.query_os())


if __name__ == "__main__":
    fingerprint()
