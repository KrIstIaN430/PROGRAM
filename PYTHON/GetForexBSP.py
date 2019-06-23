import requests
from bs4 import BeautifulSoup

#base_url = "https://www.nytimes.com/"
base_url = "http://www.bsp.gov.ph/statistics/statistics_exchrate.asp"
r = requests.get(base_url)


bsp_soup = BeautifulSoup(r.text, features="lxml")


def get_rates():
    for x in bsp_soup.find_all("tr", height="15", style="height:11.25pt"):
        if x.find_all('td')[1].get_text() == "SAUDI ARABIA": SAR = x.find_all('td')[6].get_text()
        if x.find_all('td')[1].get_text() == "KOREA": KRW = x.find_all('td')[6].get_text()
    return float(SAR), float(KRW)


def get_rates2():
    HKD = bsp_soup.find(string="HONGKONG").find_parent().find_next_siblings()[-1].get_text()
    SGD = bsp_soup.find(string="SINGAPORE").find_parent().find_next_siblings()[-1].get_text()
    return float(HKD), float(SGD)


def get_all_rates():
    for i in range(1, 33):
        temp_list = bsp_soup.find(string=i).find_parent().find_next_siblings()
        print(temp_list[0].get_text().title() + " = " + temp_list[-1].get_text())


if __name__ == '__main__':
    SAR, KRW = get_rates()
    print("SAR = " + str(SAR) + "\nKRW = " + str(KRW))
    HKD, SGD = get_rates2()
    print("HKD = " + str(HKD) + "\nSGD = " + str(SGD))
    print("--------------------------------------------------")
    get_all_rates()
