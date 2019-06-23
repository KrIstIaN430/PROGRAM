from bs4 import BeautifulSoup
import requests
#Print all pages
main_url = "https://www.theregister.co.uk/2014/08/26/top_ten_gaming_keyboard_and_mouse_combos/"
parameter = "page"
css_selector = "[id='article'] p"


def get_article_pages(url, page_parameter):
    article_pages = []

    page_number = 1
    while True:
        url_to_check = url + "?" +  page_parameter + "=" + str(page_number)
        response = requests.get(url_to_check)
        if response.status_code == 200:
            article_pages.append(url_to_check)
            page_number += 1
        else:
            break
    return article_pages


def get_html(html):
    return BeautifulSoup(html, "html.parser")


def use_selector(html, selector):
    return html.select(selector)


def print_article_text(main_link, param, css_selector):
    urls = get_article_pages(main_link, param)

    for i in urls:
        html = requests.get(i)
        r_html = html.text
        soup = get_html(r_html)
        for x in use_selector(soup, css_selector):
            print(x)
        print("=================================== NEW PAGE ==================================")


if __name__ == "__main__":
    print_article_text(main_url, parameter, css_selector)