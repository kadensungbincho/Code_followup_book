from scrapy.contrib.spiders import CrawlSpider, Rule
# from scrapy.selector import Selector
# from scrapy import Spider
from wikiSpider.items import Article
from scrapy.contrib.linkextractors import LinkExtractor


class ArticleSpider(CrawlSpider):
    name = "article"
    allowed_domains = ["en.wikipedia.org"]
    start_urls = [
                "http://en.wikipedia.org/wiki/Python_%28programming_language%29"]
    rules = [Rule(LinkExtractor(allow=("(/wiki/)((?!:).)*$"),),
                                    callback="parse_item", follow=True)]

    def parse(self, response):
        item = Article()
        title = response.xpath('//h1/text()')[0].extract()
        print("Title is: "+title)
        item['title'] = title
        return item
