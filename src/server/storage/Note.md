# Git

### git command

```python
git init 初始化
git reset --hard [版本号] 重置

git add [a.html] 添加暂存区
git rm --cached [a.html] 从暂存区删除
git commit -m ["init"] [a.html] 提交本地库

git merge [hot-fix]合并分支
解决冲突后，使用git commit 不能带文件名

git remote -v 查看当前所有远程库地址别名
git remote add 别名 远程地址 
git push [TIOE] master 推送master分支到远程TIOE项目
git pull 
git clone [https://...] 1.拉取代码 2.初始化本地仓库 3.创建别名
```

### git 团队协作

![1697900455869](C:\Users\张杰\AppData\Roaming\Typora\typora-user-images\1697900455869.png)

![1697900476334](C:\Users\张杰\AppData\Roaming\Typora\typora-user-images\1697900476334.png)

​                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               

# Python

## Scrapy框架

### scrapy流程

> 案例：dangdang  多管道
>
> ​	  movie 二级访问链接

```python
1.scrapy startproject [project_name] 创建名为project_name的爬虫框架

2.cd [project_name]/[project_name]/spiders

3.scrapy genspider [baidu] [https://www.baidu.com] 创建爬虫文件baidu 爬取https://www.baidu.com

4.scarpy crawl [baidu] 执行baidu爬虫
```

### scrapy工作原理

```python
引擎向spiders要url
引擎将要爬取的url给调度器
调度器将url放入队列
队列出队请求
引擎将请求交给下载器处理
下载器向互联网发送请求，将数据返回引擎
引擎将数据返回spiders处理
spiders xpath解析结果->引擎
if 数据->管道处理  
else url->调度器
```

### response的属性和方法

```python
response.text #字符串
response.body #二进制

selector = response.xpath() #使用xpath,返回selector对象
response.extract() #提取seletor对象的data属性值
response.extract_first() #提取selector列表的第一个数据

```

### scrapy shell 调试工具

```python
1.进入scrapy shell 终端， 直接在cmd窗口输入 
scrapy shell [www.baidu.com]
2.在ipython中进行调试
```

### CrawlSpider

```python
1.导包 
from scrapy.linkextractors import LinkExtractor
2.说明：继承自scrapy.Spider
		CrawlSpider可以定义规则，在解析html内容时候，可以根据链接规则提取处指定的链接，然后再向这些链接发送请求，适合于跟进链接
3.提取链接
scrapy.linkextractors.LinkExtractor(
	allow = ()		#正则表达式链接
    restrict_xpaths = () #xpath链接
    restrict_css = () 	 #提取符合css选择器的链接
)
eg:link = LinkExtractor(allow = 'list_23_\d+\.html')
    
```

### CrawlSpider案例

```python
1.创建项目
2.scrapy genspider -t crawl [read] [https://www.dushu.com/book/1188.html]
```

> 案例：readbook 跟进链接、数据入库







  