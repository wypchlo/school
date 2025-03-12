f = open("sygnaly.txt", "r")
lines = f.readlines()

for i in range(len(lines) // 40):
    line = lines[(i + 1) * 40 - 1]
    print(line[9], end="")

