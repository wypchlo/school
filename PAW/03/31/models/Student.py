__copyright__ = "Zespół Szkół Komunikacji";
__author__ = "Miłosz Wypchlo 4C"

from datetime import date, datetime;

class Student:#{
    def __init__(self, id: int, first_name: str, last_name: str, birth_date: date):#{
        self._id = id; 
        self.first_name = first_name;
        self.last_name = last_name;
        self.birth_date = birth_date;
    #}

    def __age(self) -> int:#{
        return datetime.now().year - self.birth_date.year;
    #}
    
    def __str__(self) -> str:#{
        return "{name} {surname} ({age})".format(name = self.first_name, surname = self.last_name, age = self.__age());
    #}

    age = property(__age);
#}
