f = open("sygnaly.txt", "r")

for line in f:
    min_letter = 'Z'
    max_letter = 'A'

    for letter in line.strip():
        if ord(letter) - ord(min_letter) < 0: 
            min_letter = letter
        if ord(max_letter) - ord(letter) < 0:
            max_letter = letter
    
    #print(line.strip(), min_letter, max_letter)
    if abs(ord(min_letter) - ord(max_letter)) <= 10: 
        print(line, end="")
