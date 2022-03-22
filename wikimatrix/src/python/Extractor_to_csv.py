import requests
from bs4 import BeautifulSoup
import pandas as pd
import pprint

link = 'https://en.wikipedia.org/wiki/Comparison_of_digital_SLRs' 

def getAllRefs(url = str) :

    response = requests.get(url) #request which gets the url's encoded content
    soup = BeautifulSoup(response.text,'html.parser') #scrape Webpage. reponse.text : url's decoded content
    #print(soup.prettify()) # To see the html code of the Wikipedia page

    httpsrefs = {}

    # Get all the references in the body of the html code
    allrefs = soup.find(id="bodyContent").find_all(rel=True) #find all tags which have the attribute "rel"
    #print(allrefs)

    for ref in allrefs: 
        string = str(ref)[-10:] # 'l">[1]</a>' for example
        condition = "["
        if condition in string :
            id = int(string[string.index("[") +1 :string.index("]")])
        if ref['rel'] == ['nofollow'] : # We are only interested in https pages and not wiki articles
            if id not in httpsrefs :
                httpsrefs[id] = [ref.get('href')]
            else :
                httpsrefs[id].append(ref.get('href'))
    #pprint.pprint(httpsrefs)
    #print(len(httpsrefs))
    return(httpsrefs) #length must be 130


def replaceRefByUrl(element, dico) : 
    if type(element) == str: #Don't take the nan's values
        r = []
        inter = ""
        t = False
        for s in element:
            if s == "]":
                t = False
                r.append(dico[int(inter)][0])
                inter = ""
            if t:
                inter += s
            if s == "[":
                t = True
        return r
    else :
        return element
              

def wikiTable_to_csv(url = str):
    tableName  = url.split("/")[-1]
    tables = pd.read_html(url, header=0) #reads html tables into a list of dataframe objects
    #print(tables)

    #If you want to test the code on linkBis, change the following "1"s by "0"s
    if not tables[1].empty:
        dataframeName = tableName + " table_urlversion.csv"

        dico = getAllRefs(link)
        tables[1]['Reference'] = tables[1]['Reference'].apply(lambda row : replaceRefByUrl(row, dico))

        tables[1].to_csv(dataframeName, sep=',') 
    #print(tables[1])

result = wikiTable_to_csv(link)
