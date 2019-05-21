import subprocess
import pprint

commands = ["date", "time", "at", "cmd", "start", "pause", "echo", "cls", "type", "more", "find", "sort", "dir", "cd",
            "md", "rd", "del", "ren", "move", "copy", "xcopy", "attrib", "fc",
            "if", "for", "set", "setlocal", "endlocal", "pushd", "popd", "shift",
            "goto", "call", "exit",
            "format", "chkdsk", "chkntfs", "subst",
            "cacls", "icacls",
            "tasklist", "taskkill"]
result = {}
for command in commands:
    a = subprocess.Popen(command + " /?", shell=True, stdout=subprocess.PIPE).stdout
    result[command] = a.read().decode('cp866')

for key in result.keys():
    file = open(key + ".txt", "w+")
    file.write("CMD: " + key + "\r\n")
    for line in result[key]:
        if line == "\n":
            continue
        else:
            file.write(line)
    file.close()
