# Domain_QA
限定域问答系统包括：自动构建知识库、问句检索、基于微信平台搭建问答系统。本项目所有代码已开源。
用户通过简单配置，可以实现快速自动化搭建一个比较完备的领域知识库。另外，基于微信平台如何通过配置来搭建问答系统，具体操作见readme.md

1 申请微信公众号（订阅号/服务号）

2 使用云服务器/Tomcat+花生壳 搭建本地服务器

3 配置微信公众平台接口。并利用源码中的：微信接口认证代码进行验证

4 提供“领域名”+“领域网站”，利用源码中的：spider-领域网站源码爬取领域网站语料，为自动抽取领域实体词准备语料

5 将领域网站语料提取正文并做分词

6 采用word2vec对上述语料进行训练，得到模型model

7 利用model及种子术语seed,获得该领域前300个候选术语

8 计算候选术语和种子术语的similar相似度，设置相似度阈值为0.6,对候选术语进行过滤

9 得到领域术语文件 seedfile.txt

10 结合seedfile.txt ,采用源码中的：spider-百科、spider-问答社区爬虫源码自动构建该领域知识库。

11 基于lucene对关系型领域知识库建立倒排索引

12 获取用户问句，对用户问句进行分析，获取关键词及限定词等

13 根据倒排索引及关键词获取初期候选问题集

14 结合问句匹配特征对候选问题集重新排序

12 将各个部分进行连接测试，完成限定域问答系统的搭建

13 总结：自动化构建领域知识库、基于领域知识库及在线社区进行问句检索、调试微信服务器及本地服务器、对用户问句进行分析

开发环境
处理器:Intel(R)Core(TM)i5-2400 Cpu@ 3.10GHz 3.10Ghz

安装内存（RAM）：6.00GB

系统类型：win32

硬盘：195GB

使用语言：JAVA、Python

IDE：Eclipse、Myeclipse、python27

服务器：Tomcat、花生壳

客户端：微信客户端

数据库：Mysql

数据库管理：PhpAdmin

网络通信： HITSZ 校内网（单个模块运行）、基于花生壳的公网（系统运行）

测试方法：单元测试、集成测试、回归测试、系统测试、黑盒测试、白盒测试

其他：微信公众平台API、Jsoup解析包、Dom解析包、phpAdmin关系型数据库管理客户端、Gensim开源工具包、Hanlp 开源工具包、Lucene开源工具包、Github托管等

文件指定位置：
E:\QA_database\website-knowledge

E:\QA_database\website-正文

E:\QA_database\website-正文预处理

E:\QA_database\QACommunity_搜搜问问

E:\QA_database\baike_infobox知识

E:\QA_database\baike

邮箱：steady_pace@126.com liqianqian  如有其它问题，可联系作者。
