__copyright__ = "Zespół Szkół Komunikacji";
__author__ = "Miłosz Wypchlo 4C"

from .Student import Student;
from .Subject import Subject;

class Grades:#{
    def __init__(self, student: Student, subject: Subject, grades: list[int] = []):#{
        self.grades = grades;
        self.subject = subject;
        self.student = student;
    #}

    def add_grade(self, grade: int):#{
        if grade < 1 or grade > 6:#{
            raise ValueError("Grade must be between 1 and 6");
        #}
        self.grades.append(grade);
    #}

    def get_grades(self):#{
        return self.grades;
    #}

    def get_average(self):#{
        average: int = 0;
        for grade in self.grades:#{
            average += grade;
        #}
        return average / len(self.grades);
    #}
#}
