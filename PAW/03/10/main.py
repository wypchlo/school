true = True
false = False

def read_graph(file_name: str) -> list[list[str]]:#{
    file = open(file_name);
    _ = file.readline();
    
    relationship_list: list[list[str]] = [];

    for line in file:#{
        verticle_neighbors = line.split(" ")[1::];
        relationship_list.append(verticle_neighbors);
    #}

    return relationship_list;
#}

def write_neighbours_list(vertices: list[list[str]]):#{
    for index, verticle in enumerate(vertices):#{
        print("Sąsiadami wierzchołka {X} są: {neighbours}".format(X = index, neighbours = ", ".join(verticle)));
    #}
#}

def list_to_matrix(vertices: list[list[str]]) -> list[list[bool]]:#{
    matrix: list[list[bool]] = [[false for col in range(len(vertices))] for row in range(len(vertices))];
    for index, neighbours in enumerate(vertices):#{
        for neighbour in neighbours:#{
            matrix[index][int(neighbour)] = true;
        #}
    #}
    return matrix;
#}

def write_matrix(vertices: list[list[bool]]):#{
    print("-", end=" ")
    for i in range(len(vertices)):#{
        print("\033[4m" + str(i), end="\033[0m ");
    #}
    print();
    for y in range(len(vertices)):#{
        print(y, end="|");
        for x in range(len(vertices)):#{
            print(int(vertices[y][x]), end=" ");
        #}
        print();
    #}
#}

def main():#{
    array: list[list[str]] = read_graph("graph.txt");
    write_neighbours_list(array);
    matrix: list[list[bool]] = list_to_matrix(array);
    write_matrix(matrix);
#}

main()
