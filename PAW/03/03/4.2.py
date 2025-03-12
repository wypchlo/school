f = open("sygnaly.txt", "r")

rozne_max = 0
slowo = ""

for line in f:
    rozne_litery = []
    for letter in line:
        if letter not in rozne_litery: 
            rozne_litery.append(letter)
    rozne_litery.remove('\n')

    if len(rozne_litery) > rozne_max:
        rozne_max = len(rozne_litery)
        slowo = line

print(slowo.strip(), rozne_max)
