import requests


main_url = "https://www.theregister.co.uk/2014/08/26/top_ten_gaming_keyboard_and_mouse_combos/"

parameter = "page"
css_selector = "[id='article'] p"


def get_pages(url):
    page_number = 1
    while True:
        url_to_check = url + "?page=" + str(page_number)
        print(url_to_check)
        response = requests.get(url_to_check)
        print(str(page_number) + ". " + str(response.status_code))
        if response.status_code == 200:
            page_number += 1
        elif response.status_code != 200:
            break
    return page_number - 1


print(str(get_pages(main_url)) + " pages")

