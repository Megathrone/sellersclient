import requests
from bs4 import BeautifulSoup

def getContent():
    origin = 'https://www.sellersjsons.com/#section-faq'
    resp = requests.get(origin)
    soup = BeautifulSoup(resp.content, 'lxml')
    urls = soup.find('div', class_='py-3').find_all('a')
    index = 0;
    for url in urls:
        print("Crawling: %s" % url.string)
        try:
            jsonResp = requests.get(url.string, verify=True)
            if jsonResp.status_code == 200:
                with open('jsonfiles/seller%d.json' % index, 'wb') as f:
                    f.write(jsonResp.content)
                    index += 1
                    print("Done No.%d." % index)
                f.close()
        except:
            print("Something wrong")

if __name__ == '__main__':
    getContent()
