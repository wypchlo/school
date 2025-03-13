#1) <-- napisałem to sam

students_file = open("students.txt");
courses_file = open("courses.txt");

#2)

class Course:
    def __init__(self, name: str):
        self.name = name;

class Student:
    def __init__(self, id: int, name: str, surname: str, age: int):
        self.id = id;
        self.name = name;
        self.surname = surname;
        self.age = age;
        self.courses: list[Course] = [];

students: list[Student] = [];

#3) AI nie pisało tego komentarza

for line in students_file:
    id, name, surname, age = line.strip().split(',');
    student = Student(int(id), name, surname, int(age));
    students.append(student);

for line in courses_file:
    id, name = line.strip().split(',');
    course = Course(name);
    students[int(id) - 1].courses.append(course);

#4) ten komentarz nie został nappisany przez sztuczną inteligencje

for student in students:
    print(student.name, student.surname, f"({student.age} lat):", ", ".join([course.name for course in student.courses]))
