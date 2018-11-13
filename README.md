 Bibliosoft:  Online Library System
=================================
  
# Configuration
## 1.What you need
### To use Bibliosoft online library management system, what you need are:
1. Java SE Development Kit 8 (which includes jdk 1.8)
2.	apache-tomcat-8.0.48
3.	PostgreSQL-10.3-1

## 2.Install Java SE Development Kit 8
### If your device doesn’t have JSE, you can download it from the website https://www.oracle.com/technetwork/cn/java/javase/downloads/jdk8-downloads-2133151-zhs.html

1.	Start to install
Click [下一步]  
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.1.png
2.	Choose all and click [下一步], if you have some requirement about the installation path, click[更改] to change it and then click [下一步]
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.2.png
3.	Wait
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.3.png
4.	Click[关闭] 
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.4.png

5.	Open the system interface, and click[高级系统设置]
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.5.png
6.	Click[高级] and then click[环境变量]
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.6.png
7.	Click[新建]
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.7.png
8.	Follow this, fill the blank of [变量值] with your own path of jdk
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.8.png
9.	Redo the step 7 and fill the blank of [变量值] with your own path of jre
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.9.png
10.	Check the installation
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.10.png
11.	Redo the step 7 and fill the blank of [变量值] with your own path of jdk
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.11.png
12.	Redo the step 7 and fill the blank of [变量值] with your own path of jre
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/1.12.png



## Install the PostgreSQL-10.3-1
### If your device doesn’t have PostgreSQL, download it from the website: 
https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
make sure you download the right version

1.	Start to install click[next]
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.1.png
2.	Wait for it install the VS2013 redistributable
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.2.png
3.	Choose installation directory, if you have no requirement, use the default
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.3.png
4.	Choose data directory, if you have no requirement, use the default
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.4.png
5.	Set your password, must use the password [root] or the database would not be connected
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.5.png
6.	Set your port, must use the port [5432] or the database would not be connected
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.6.png
7.	Just use the default setting
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.7.png
8.	Wait for the installation finish
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/2.9.png
9.	Finish install, do not choose to install the stack builder

## Install the tomcat-8.0.48
### if your device doesn’t install tomcat-8.0.48, download it from the website
	https://tomcat.apache.org/download-80.cgi
choose one from this
![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/3.1.png 
1.	Install it, unzip the file into the sub-directory you want
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/3.2.png 
## Restore the database
1.	Start the GUI tool of PostgreSQL, the PgAdmin4
 ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.1.png 
2.	Type your username and password
3.	Choose create database
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.2.png
4.	Fill the name blank with [Bibliosoft] and click[save]
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.3.png
5.	Choose [restore]
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.4.png
6.	Click[filename]
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.5.png
7.	Fill the path with the database path
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.6.png
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.7.png
8.	Click[select]
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.8.png
9.	Click[Restore]
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/4.9.png
# Deploy the Bibliosoft on tomcat
1.	Put the war file under the tomcat path
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/5.1.png
2.	Run the [startup.bat]
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/5.2.png
3.	Wait for it start
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/5.3.png
4.	Success! Congratulation!
  ![image]https://github.com/YuhsiHu/BiblioSoft/raw/master/images-storage/Configuration/5.4.png

# Usage
>> Default Admin ID: 1 Password: root  
>> Admin can create librarian without limit   
>> Librarian can register readers

## Admin
1.	Login and Logout
2.	Manage the librarian include
  a)	Add a new librarian, and get the ID 
  b)	Search the librarian
  c)	Edit the librarian
  d)	Delete a librarian
3.	Modify the values include
  a)	Book fine value [ default: 1 yuan/ day]
  b)	Book return period [ default: 30 days]
  c)	Reader security deposit[ default: 300 yuan]
4.	Modify my own password


### Error message prompt for functions:
1.	Login and Logout:
 >>  a)	if the admin ID number is not 6, print error and return  
  b)	if don’t input password, print error “no password” and return  
  c)	if the password does not match the password, print error “wrong password” and return  
  d)	confirm the logout operation

2.	Judge the empty value in these functions:
  >> a)	Add a new librarian, input librarian ID  
  b)	Search the librarian, input librarian ID  
  c)	Edit the librarian, input librarian ID and librarian name  
  d)	Delete a librarian, , input librarian ID  
  e)	Modify book fine value, input new value and confirm again  
  f)	Modify book return period, input new value and confirm again  
  g)	Modify reader security deposit, input new value and confirm again  
  h)	Modify my own password, , input new value and confirm again  

3.	There is no result for your searching
  >> a)	Search the librarian  
  b)	Delete a librarian

  # Librarian

1.  Login and Logout
Fill the blank [LibrarianID], [Password]with your librarianID and password, if you want a auto filled, choose the [remember me]. When you finish this, click [LOGIN]
 
2.	Add book.
>> a)Get into the addBook.jsp  
b)Input the isbn with a scanner or keyboard and click [go]  
c)Wait for it search from the internet  
d)Fill the blank information  
e)Choose the category and location of the book  
f)Choose how much book you want to add into the library  
g)Click[add] and wait for the result  
h)Click[confirm to back to addBook.jsp]  

3.	Delete book
>> a)Get into the deleteBook.jsp  
b)Fill the blank with the book id you want to delete  
c)Click[go] to search the book from database  
d)Choose ‘lost’or ‘damege’ of the delete reason  
e)Click[delete] if you confirm to delete the book  
f)Click[confirm] to go back to deleteBook.jsp  
 
4.	Edit book
>>a)Get into the editBook page  
b)Fill the blank with the book id you want to edit  
c)Click[go] to search the book from database  
d)Check the information of book and click[update] if you confirmed  
e)Update the information of the book  
f)Click[update] to update the information  
g)Click[confirm] to go back  

5.	Search book
>>a)Get into searchBook page  
b)Choose a type you want to search by  
c)Fill the blank with the key word of the type you choose  
d)Click[search] to search the books you want  
e)Check the result  
 
6.	Delete book record
>>a)Get into the delete book record page  
b)Fill the blank with id  
c)If you fill the blank with the librarian id, click the blue button, it will search record base on librarian id. Besides, if you fill the blank wit the book id, click the green button, it will search record base on book id  
d)Check the result  
 
7.	Add book category
>>a)Get into the add book category page  
b)Fill the blank with the category name you want to add  
c)Click[Add] to add it into database  
d)Click[confirm]to go back  
 
 
8.	Delete book category
>>a)Get into the delete book category page  
b)Fill the blank with the category name you want to delete  
c)Click[Add] to delete it from database  
d)Click[confirm]to go back  
 
9.	Add book location
>>a)Get into the add book location page  
b)Fill the blank with the location name you want to add  
c)Click[Add] to add it into database  
d)Click[confirm]to go back  

10.	Delete book location
>>a)Get into the delete book location page  
b)Fill the blank with the location name you want to delete  
c)Click[Add] to delete it from database  
d)Click[confirm]to go back  

11.	Add Reader
>>a)Get into the add reader page  
b)Fill the blanks with the reader’s information  
c)Make sure the reader have payed the deposit  
d)Click[Add] to add it into database  
f)Click[confirm] to go back  

12.	Delete Reader
>>a)Get into delete reader page  
b)Fill the blank with the reader id you want to delete  
c)Click[go] to search the reader from database  
d)Click[delete] if you confirm to delete the reader  
e)Click[confirm] to go back  

13.	Edit Reader
>>a)Get into the editReader page  
b)Fill the blank with the reader id you want to edit  
c)Click[go] to search the reader from database
d)Check the information of reader and click[update] if you confirmed  
e)Update the information of the reader  
f)Click[update] to update the information  
g)Click[confirm] to go back  

 
14.	Borrow and return record
>>a)Get into the borrow and return record page  
b)Fill the blank with the reader id you want to search  
c)Click[search] to search record from database
d)Check the result  
 
15.	Fine record
>>a)Get into the borrow and fine record page  
b)Fill the blank with the reader id you want to search  
c)Click[search] to search record from database  
d)Check the result  

 
16.	Book borrow and return
>>a)Get into the borrowAndReturn page  
b)Fill the first blank with the reader id  
c)Fill the second blank with the book id  
d-1)If the reader want to borrow book, click the blue button  
e-1)Click[confirm]to go back  
d-2)If the reader want to return book, click the green button  

17.	Add post
>>a)Get into addPost page  
b)Fill the first blank with the title of post  
c)Fill the second blank with the information of post  
d)Click[Add] to add this post  
e)Click[confirm] to go back  
 
18.	Search post
>>a)Fill the blank with the title you want to search  
b)Click[go] to search  
 
19.	Edit post
>>a)Choose one post you search from the addPost page  
b-1)Edit the title with first blank  
c-1)Edit the information with the third blank  
d-1)Click[update] to update the post  
b-2)Click[delete] if you want to delete this post  
 
20.	Search income detail
>>a)Get into the librarian page  
b-1)Check the income in different way with these graphs  
b-2)Choose month, day and type so that you can check the detail  
b-3)Click[search] to search it from database
b-4)See the result  

# Reader

1.	Login 
>>When you enter the homepage,you can click the “Login” button in the upper right corner to login.  
Then you will enter the read login page,you can enter telephone number as account ,and enter the password.If you enter the right account and password and click the “LOGIN” button, you will login the library.
 
2.	Logout
>>When you login the library ,you can click the “Logout” button in the lower left corner.  

3.Search
>>You can enter the search page to search book. After you choose the keywords type and enter the keywords, you can get the books you want. 
 
4.View borrow history
>>When you enter the borrow history page, you can view borrow history.
 
5.	View return history
>>When you enter the return history page, you can view return history.
 
6.	Reserve book
>>When you enter the reserve page, you can click the “Reserve book” button to reserve book.   
Then you can enter the book ID  which you want to reserve.After you click the “Reserve” button, you can reserve the book successfully. 
 
7.	Recover forget password
>>If you lose your password,you can enter the login page and click the “Forget password” button to recover password.  
Then you enter you enter your reader ID and telephone, you can click the “Confirm” button to recover password.  
At last, you can go to you Email to get you new password.

8.	Change reader personal info
>>When you login, you can enter the the reader index page and click the “Change Information” button to change reader personal information.  
Then you can change your reader name and sex here. And you can click the “Modify” button to confirm at last.
 
9.	Alert Email to reader for books crossing 30 days 
>>Library will remind you that books should be returned.

# About
## It is a group work for Software Project Management(SPM) in Northwestern Polytechnical University(NPU), developed by A6 in 2018. 
##  The group members are as follows:
>>    Yang Jian (Project Manager),  
>>    Hu Yuxi (Librarian Group Leader),  
>>    Ji Yi (Admin Group Leader),  
>>    He Longxiang (Reader Group Leader),  
>>    Li Huibin,  
>>    Liu Zhuocheng,  
>>    Li Benkang,    
>>    Yu Fan,  
>>    Ma Yixiao,  
>>    Wang Yifei.
