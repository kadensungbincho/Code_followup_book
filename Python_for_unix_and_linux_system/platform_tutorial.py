#!/usr/bin/env python
import platform

profile = [
    # the commented are unavailable for osx
    # platform.architecture(),
    # platform.dist(),
    # platform.libc_ver(),
    platform.mac_ver(),
    platform.machine(),
    platform.node(),
    platform.platform(),
    platform.processor(),
    platform.python_build(),
    platform.python_compiler(),
    platform.python_version(),
    platform.system(),
    platform.uname(),
    platform.version()
    ]


if __name__ == "__main__":
    for item in profile:
        print(item)
