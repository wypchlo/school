__copyright__ = "Zespół Szkół Komunikacji";
__author__ = "Miłosz Wypchlo 4C"

def year_grade(average: float) -> int:#{
    if average < 1.85: return 1;
    if average < 2.7: return 2;
    if average < 3.7: return 3;
    if average < 4.7: return 4;
    if average < 5.5: return 5;
    return 6;
#}
