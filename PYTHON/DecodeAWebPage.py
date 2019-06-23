import requests
from bs4 import BeautifulSoup
# Get the title of all the articles
base_url = 'http://www.nytimes.com'
r = requests.get(base_url)
soup = BeautifulSoup(r.text, features="lxml")
ctr = 0
for story_heading in soup.select("h2.esl82me0"):
    print(story_heading.get_text())
    ctr += 1
print(ctr)
