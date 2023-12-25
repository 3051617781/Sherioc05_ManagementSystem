"""04json文件
取出所有省名，城市名组成列表
"""
"""
json.loads(s) 将str中的json解码为py数据类型
json.load(fp) 将fp....

"""
import json
def read_file(path:str):
    with open(path,'r',encoding='utf-8')as f:
        data = json.load(f)
    return data


path = './src/省市json文件.json'
data = read_file(path)
result_ls = []
# for item in data:
#     temp_dict = {}
#     temp_dict['province'] = item['label']
#     temp_dict['city'] = [i['label']for i in item['children']]
#     result_ls.append(temp_dict)
result_ls = [{item['label']:[i['label'] for i in item['children']]}for item in data]
print(result_ls)

"""保存为json数据
json.dumps(obj, *, ensure_ascii=True, indent=None, sort_keys=False)
json.dump(obj, fp, *, ensure_ascii=True, indent=None, sort_keys=False)
方法	           描述
json.dumps(obj)	    将Python 数据类型obj对象编码成JSON数据，写入内存。
json.dump(obj,fp)	将Python 数据类型obj对象编码成JSON数据，写入磁盘文件对象fp中。

json 中默认ensure_ascii=True， 会将中文等非ASCII 字符转为unicode 编码（形如\uXXXX），设置ensure_ascii=False 可以禁止JSON 将中文转为unicode 编码，保持中文原样输出。
Python中的字典数据转为JSON 默认不排序。可设置sort_keys=True使转换结果按照字典升序排序（a-z）。
indent 参数可用来对JSON 数据进行格式化输出，默认值为None，不做格式化处理，可设一个大于0 的整数表示缩进量，例如indent=4。输出的数据被格式化之后，变得可读性更好。

"""