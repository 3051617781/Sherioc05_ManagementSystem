"""
05 读取excel文件，统计各大类报名作品数，按大类将不同的报名组写入不同的工作表
"""
"""
python操作excel主要用到xlrd和xlwt两个库
xlrd用于读excel，官方文档：https://xlrd.readthedocs.io/en/latest/
xlwt用于写excel，官方文档：https://xlwt.readthedocs.io/en/latest/
"""
import xlrd
import xlwt
data = xlrd.open_workbook('./src/武汉理工大学校赛的参赛作品.xls')
table = data.sheets()[0]
# print(table.nrows)    table的行数
type_value = table.col_values(1)[1:] #  读取工作表第二列的数据，并切片去除该列第一行（标题行的）数据，获得所有作品所属大类
type_unique = list(set(type_value))
type_cnt = {}
for item in type_unique:
    type_cnt[item] = type_value.count(item)

#写入工作表
work_book = xlwt.Workbook()
for type_name in type_unique:
    work_sheet = work_book.add_sheet(type_name)  # 增加sheet表，表名为大类名
    row_id = 0  # 写入时的行号
    # 遍历所有行号
    for row in range(table.nrows):
        col_id = 0  # 写入时的列号
        row_value = table.row_values(row) # 获取该行的数据
        if row == 0 or row_value[1] == type_name:  # 如果是首行（标题行）或类别与要求一致，则该行需要写入
            for value in row_value:
                #  遍历该行数据，依次写入由row_id, col_id指定的单元格，每写一个列号加1
                work_sheet.write(row_id, col_id, value)  
                col_id = col_id+1
            row_id = row_id + 1  # 写完一行，行号加1
work_book.save('result.xls')  # 保存为硬盘文件

print(type_cnt)