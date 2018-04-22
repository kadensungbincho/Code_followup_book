#!/home/kadencho/anaconda3/bin/python

import subprocess

# Command 1
def uname_func():
    uname = "uname"
    uname_arg = "-a"
    print("Gathering system information with {} command:\n".format(uname))
    subprocess.call([uname, uname_arg])

# Command 2
def disk_func():
    diskspace = "df"
    diskspace_arg = "-h"
    print("Gathering diskspace information {} command:\n".format(diskspace))
    subprocess.call([diskspace, diskspace_arg])

# Main function that call other functions
def main():
    uname_func()
    disk_func()

main()
