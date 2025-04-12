__copyright__ = "Zespół Szkół Komunikacji";
__author__ = "Miłosz Wypchlo 4C"

from models.Teacher import Teacher;
from models.Subject import Subject;
from models.Student import Student;
from models.Grades import Grades;
from year_grade import year_grade;
import datetime;
import json;

teachers: list[Teacher] = [];
subjects: list[Subject] = [];
students: list[Student] = [];
grades: list[Grades] = [];

with open("teachers.txt") as file:#{
    for line in file:#{
        id, name, surname = line.strip().split(' ');
        teachers.append(Teacher(int(id), name, surname));
    #}
#}

with open("subjects.txt") as file:#{
    for line in file:#{
        id, name, teacher_id = line.strip().split(' ');
        teacher: Teacher | None = next((tchr for tchr in teachers if tchr._id == int(teacher_id)), None);

        if not teacher: continue; 

        subjects.append(Subject(int(id), name, teacher));
    #}
#

with open("students.txt") as file:#{
    for line in file:#{
        id, name, surname, birth_date = line.strip().split(' ');
        students.append(Student(int(id), name, surname, datetime.datetime.strptime(birth_date, '%Y-%m-%d').date()));
    #}
#}}

with open("grades.txt") as file:#{
    for line in file:#{
        student_id, subject_id, grades_raw = line.strip().split(' ');
        student: Student | None = next((obj for obj in students if obj._id == int(student_id)), None);
        subject: Subject | None = next((obj for obj in subjects if obj._id == int(subject_id)), None);

        if not student or not subject: continue;

        grades_arr: list[int] = list(map(int, grades_raw.split(',')));

        grades.append(Grades(student, subject, grades_arr));
    #}
#}}

print("Oceny i średnie poszczególnych uczniów");

for student in students:#{
    print("{}:".format(student));
    for data in [obj for obj in grades if obj.student == student]:#{
        print("    {}:".format(data.subject.name));
        print("        Oceny: {}".format(", ".join(list(map(str, data.grades)))));
        print("        Średnia: {:.2f}".format(data.get_average()));
        print("        Ocena końcowa: {}".format(year_grade(data.get_average())));
    #}
    print();
#}

with open("students.json", "w") as file:#{
    data_dict: dict = {};

    for student in students:#{
        data_dict[str(student)] = dict();
        for data in [obj for obj in grades if obj.student == student]:#{
            data_dict[str(student)][data.subject.name] = {
                "Oceny": ", ".join(list(map(str, data.grades))),
                "Srednia": round(data.get_average(), 2),
                "Ocena roczna": year_grade(data.get_average())
            }
        #}
    #}

    json.dump(data_dict, file, indent=4);
#}

print("="*50);
print();

for subject in subjects:#{
    print("{}:".format(subject.name));
    print("\tNauczyciel: {}".format(subject.teacher));
    
    total_grades: list[int] = sum([obj.grades for obj in grades if obj.subject.name == subject.name], []);

    print("\tOceny: {}".format(", ".join(map(str, total_grades))));
    print("\tŚrednia: {}".format(round(sum(total_grades)/len(total_grades), 2)));
    print();
#}

with open("subjects.json", "w") as file:#{
    data_dict: dict = {};
    
    for subject in subjects:#{
        data_dict[subject.name] = dict();
        data_dict[subject.name]["Nauczyciel"] = str(subject.teacher);
        
        total_grades: list[int] = sum([obj.grades for obj in grades if obj.subject.name == subject.name], []);
        
        data_dict[subject.name]["Oceny"] = total_grades;
        data_dict[subject.name]["Srednia"] = round(sum(total_grades)/len(total_grades), 2);
    #}

    json.dump(data_dict, file, indent=4);
#}
