__copyright__ = "Zespół Szkół Komunikacji";
__author__ = "Miłosz Wypchlo 4C"

class Teacher:#{
    def __init__(self, id: int, name: str, surname: str):#{
        #czmu w tym jest surname i name a w poprzednim juz nie xd 
        self._id = id;
        self.name = name;
        self.surname = surname;
    #}

    def __str__(self):#{
        return "{name} {surname}".format(name = self.name, surname = self.surname);
    #}
#}
