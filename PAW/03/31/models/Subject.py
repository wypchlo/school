__copyright__ = "Zespół Szkół Komunikacji";
__author__ = "Miłosz Wypchlo 4C"

from .Teacher import Teacher;

class Subject:#{
    def __init__(self, id: int, name: str, teacher: Teacher):#{
        self._id = id;
        self.name = name;
        self.teacher = teacher;
    #}

    def __str__(self):#{
        return "{name} {teacher}".format(name = self.name, teacher = self.teacher);
    #}
#}
