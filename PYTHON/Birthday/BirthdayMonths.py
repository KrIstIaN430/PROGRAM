import json
from collections import Counter

month = []
months = {
    1: "January",
    2: "February",
    3: "March",
    4: "April",
    5: "May",
    6: "June",
    7: "July",
    8: "August",
    9: "September",
    10: "October",
    11: "November",
    12: "December"
}
with open("info.json", "r") as open_file:
    birthday_dict = json.load(open_file)

for x in birthday_dict.values():
    month.append(x.split()[0])

counter_m = Counter(month)

if __name__ == "__main__":
    print(counter_m)



else:
    sorted_month_counter = {}
    for i in range(1, 13):
        j = month.count(months[i])
        if j > 0:
            sorted_month_counter[months[i]] = j
    print(sorted_month_counter)
    x = []
    y = []
    for i in sorted_month_counter:
        x.append(i)
        y.append(sorted_month_counter[i])