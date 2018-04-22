#!/home/ubuntu/anaconda3/bin/python
import subprocess

# Command 1 
uname = "uname"
uname_arg = "-a"
print("Gathering system information with %s command:\n" % uname)
subprocess.call([uname, uname_arg])

# Command 2 
diskspace = "df"
diskspace_arg = "-h"
print("Gathering diskspace information {} command:\n".format(diskspace))
subprocess.call([diskspace, diskspace_arg])
