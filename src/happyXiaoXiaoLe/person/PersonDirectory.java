/**
 *
 */
package happyXiaoXiaoLe.person;

import happyXiaoXiaoLe.Director;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * @author Mxkun ,yishui
 * @version 1.8.0_301
 */
public class PersonDirectory {

	/**
	 * 创建一个PersonDirectory单例对象
	 * 便于后面静态调用
	 */
	private static PersonDirectory instance = new PersonDirectory();

	/**
	 * 创建一个Persons对象
	 * 储存所有用户信息
	 */
	private static ArrayList<Person> persons = new ArrayList<>();

	/**
	 * 获取单例对象
	 *@param
	 *@return happyXiaoXiaoLe.person.PersonDirectory
	 */
	public static PersonDirectory getInstance() {

		return instance;
	}

	/**
	 * 添加用户
	 *@param name, id, password, email, phone
	 *@return void
	 */
	public void addPerson(String name, String id, String password, String email, String phone) {

		getPersons().add(new Person(name, id, password, email, phone));
	}

	/**
	 * 注册时添加新用户
	 * @param name
	 * @param password
	 * @param email
	 * @param phone
	 */
	public void addNewPerson(String name, String password, String email, String phone) {
		getPersons().add(new Person(name, password, email, phone));
	}

	/**
	 * 通过账号信息获取用户对象
	 *@param id
	 *@return happyXiaoXiaoLe.person.Person
	 */
	public Person getPerson(String id) {

		for (Person index : persons) {
			if (index.getId().equals(id)) ;
			return index;
		}
		return null;
	}

	/**
	 * 删除参数中的用户对象
	 *@param person
	 *@return void
	 */
	public void removePersons(Person person) {

		getPersons().remove(person);
	}

	/**
	 * 通过id删除用户
	 * @param id
	 */
	public void removePersons(String id) {
		for (int i = 0; i < getNumberOfPerson(); i++) {
			if (getPersons().get(i).getId().equals(id)) ;
			removePersons(getPersons().get(i));
		}
	}

	/**
	 * 根据用户在arraylist中的序号查找用户对象
	 *@param index
	 *@return happyXiaoXiaoLe.person.Person
	 */
	public Person getPerson(int index) {

		return getPersons().get(index);
	}

	/**
	 * 获取用户数量
	 *@param
	 *@return int
	 */
	public int getNumberOfPerson() {

		return getPersons().size();
	}

	/**
	 * 迭代器，迭代ArrayList<Person>的数据
	 *@param
	 *@return java.util.Iterator<happyXiaoXiaoLe.person.Person>
	 */
	public Iterator<Person> iterator() {

		return getPersons().iterator();
	}

	/**
	 * 获取用户列表
	 *@param
	 *@return java.util.ArrayList<happyXiaoXiaoLe.person.Person>
	 */
	public ArrayList<Person> getPersons() {

		return persons;
	}

	/**
	 * 重新设置用户列表信息
	 *@param persons
	 *@return void
	 */
	public void setPersons(ArrayList<Person> persons) {

		this.persons = persons;
	}

	/**
	 * 将所有用户信息写入储存文件
	 * @param
	 * @return void
	 */
	public void writePerson() {
		FileWriter writeFile = null;
		try {
			//1.数据想写入的路径及文件
			File file = new File("data/PersonData/personList.txt");
			//2.如果该文件不存在，就创建
			if (!file.exists()) {
				file.createNewFile();
			}
			//3.给字节输出流赋予实例
			writeFile = new FileWriter(file);
			//4.通过循环将数组写入txt文件中
			for (int i = 0; i < PersonDirectory.getInstance().getNumberOfPerson(); i++) {
				writeFile.write(PersonDirectory.getInstance().getPerson(i).toString());
				//5.加上换行符
				writeFile.write("\n");
			}
			//6.把writeFile里的数据全部刷新一次，全部写入文件中
			writeFile.flush();
		} catch (Exception e1) {//10.异常捕获
			e1.printStackTrace();
		} finally {
			try {
				//7.如果writeFile不为空，就将其关闭
				if (writeFile != null)
					writeFile.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * 读取用户信息
	 * 并全部存储在ArrayList people中
	 *
	 * @param FileName
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Person> creatPerson(String FileName) throws IOException {
		ArrayList<Person> people = new ArrayList<>();
		//1.声明一个字符输入流
		FileReader reader = null;
		//2.声明一个字符输入缓冲流
		BufferedReader readerBuf = null;

		try {
			//3.通过BufferedReader包装字符输入流
			readerBuf = new BufferedReader(new FileReader(FileName));
			//4.创建一个集合，用来存放读取的文件的数据
			ArrayList<String> strList = new ArrayList<>();
			//5.用来存放一行的数据
			String lineStr;
			//6.逐行读取txt文件中的内容
			while ((lineStr = readerBuf.readLine()) != null) {
				//7.把读取的行添加到list中
				strList.add(lineStr);
			}
			//8.循环遍历集合，将集合中的数据放入数组中
			for (String str : strList) {
				StringTokenizer tokenizer = new StringTokenizer(str, "-");
				if (tokenizer.countTokens() == 5) {
					String name = tokenizer.nextToken();
					String id = tokenizer.nextToken();
					String password = tokenizer.nextToken();
					String phone = tokenizer.nextToken();
					String email = tokenizer.nextToken();
					people.add(new Person(name, id, password, phone, email));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//9.关闭字符输入流
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//10.关闭字符输入缓冲流
			try {
				if (readerBuf != null)
					readerBuf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return people;
	}

	/**
	 * 读取文件Adventure.txt里的数据
	 *
	 * @return
	 */
	public boolean readPersonAdventure(String id) {
		File file = new File("data/PersonData/Person/" + id + "/Adventure.txt");
		if (!file.exists())
			return false;

		//1.声明一个字符输入流
		FileReader reader = null;
		//声明一个字符输入缓冲流
		BufferedReader readerBuf = null;

		try {
			//3.通过BufferedReader包装字符输入流
			readerBuf = new BufferedReader(new FileReader(file));
			//4.创建一个集合，用来存放读取的文件的数据
			ArrayList<String> strList = new ArrayList<>();
			//5.用来存放一行的数据
			String lineStr;
			//6.逐行读取txt文件中的内容
			while ((lineStr = readerBuf.readLine()) != null) {
				//7.把读取的行添加到list中
				strList.add(lineStr);
			}
			//8.循环遍历集合，将集合中的数据放入数组中
			for (String str : strList) {
				StringTokenizer tokenizer = new StringTokenizer(str, "-");
				if (tokenizer.countTokens() == 3) {
					int level = Integer.parseInt(tokenizer.nextToken());
					int score = Integer.parseInt(tokenizer.nextToken());
					int result = Integer.parseInt(tokenizer.nextToken());
					if(Director.getInstance().getUser().getAdventureData()[level-1][0]<score){
						Director.getInstance().getUser().setAdventureData(level-1,score,result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//9.关闭字符输入流
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//10.关闭字符输入缓冲流
			try {
				if (readerBuf != null)
					readerBuf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 读取文件Jewel.txt里的数据
	 *
	 * @return
	 */
	public boolean readPersonJewel(String id) {
		File file = new File("data/PersonData/Person/" + id + "/Jewel.txt");
		if (!file.exists())
			return false;

		//1.声明一个字符输入流
		FileReader reader = null;
		//声明一个字符输入缓冲流
		BufferedReader readerBuf = null;

		try {
			//3.通过BufferedReader包装字符输入流
			readerBuf = new BufferedReader(new FileReader(file));
			//4.创建一个集合，用来存放读取的文件的数据
			ArrayList<String> strList = new ArrayList<>();
			//5.用来存放一行的数据
			String lineStr;
			//6.逐行读取txt文件中的内容
			while ((lineStr = readerBuf.readLine()) != null) {
				//7.把读取的行添加到list中
				strList.add(lineStr);
			}
			//8.循环遍历集合，将集合中的数据放入数组中
			for (String str : strList) {
				StringTokenizer tokenizer = new StringTokenizer(str, "-");
				if (tokenizer.countTokens() == 3) {
					int level = Integer.parseInt(tokenizer.nextToken());
					int score = Integer.parseInt(tokenizer.nextToken());
					int result = Integer.parseInt(tokenizer.nextToken());
					if(Director.getInstance().getUser().getJewelData()[level][0]<score){
						Director.getInstance().getUser().setJewelData(level,score,result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//9.关闭字符输入流
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//10.关闭字符输入缓冲流
			try {
				if (readerBuf != null)
					readerBuf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 读取文件passData里的数据
	 *
	 * @return
	 */
	public boolean readPersonGameData(String id) {

		File file = new File("data/PersonData/Person/" + id + "/passData.txt");
		if (!file.exists())
			return false;

		//1.声明一个字符输入流
		FileReader reader = null;
		//声明一个字符输入缓冲流
		BufferedReader readerBuf = null;

		try {
			//通过BufferedReader包装字符输入流
			readerBuf = new BufferedReader(new FileReader(file));
			//用来存放一行的数据
			String lineStr;
			//逐行读取txt文件中的内容
			if ((lineStr = readerBuf.readLine()) != null) {
				Director.getInstance().getUser().setAdventureLevel(Integer.parseInt(lineStr));
			}
			if ((lineStr = readerBuf.readLine()) != null) {
				Director.getInstance().getUser().setEndlessScore(Integer.parseInt(lineStr));
			}
			if ((lineStr = readerBuf.readLine()) != null) {
				Director.getInstance().getUser().setJewelLevel(Integer.parseInt(lineStr));
			}
			if ((lineStr = readerBuf.readLine()) != null) {
				Director.getInstance().getUser().setAchievement(Integer.parseInt(lineStr));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//9.关闭字符输入流
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//10.关闭字符输入缓冲流
			try {
				if (readerBuf != null)
					readerBuf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean txtLogonCreat(String id) {

		File file1 = new File("data/PersonData/Person/" + id);
		if (!file1.isDirectory()) {
			file1.mkdir();
		}
		File fileAdventure = new File("data/PersonData/Person/" + id + "/Adventure.txt");
		if (!fileAdventure.exists()) {
			try {
				fileAdventure.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File fileEndless = new File("data/PersonData/Person/" + id + "/Endless.txt");
		if (!fileEndless.exists()) {
			try {
				fileEndless.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File fileJewel = new File("data/PersonData/Person/" + id + "/Jewel.txt");
		if (!fileJewel.exists()) {
			try {
				fileJewel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File filePassData = new File("data/PersonData/Person/" + id + "/passData.txt");
		if (!filePassData.exists()) {
			try {
				filePassData.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			FileWriter writeFile = null;
			try {
				writeFile = new FileWriter(filePassData);
				writeFile.write("1"+"\n");
				writeFile.write("0"+"\n");
				writeFile.write("1"+"\n");
				writeFile.write("0");
				writeFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 在Adventure文件中追加数据
	 *
	 * @param str
	 * @return
	 */
	public boolean writeAdventure(String str) {

		File file = new File("data/PersonData/Person/" + Director.getInstance().getUser().getId() + "/Adventure.txt");
		return appendData(str, file);
	}

	public boolean writeEndless(String str) {
		File file = new File("data/PersonData/Person/" + Director.getInstance().getUser().getId()+ "/Endless.txt");
		return appendData(str, file);
	}

	public boolean writeJewel(String str) {
		File file = new File("data/PersonData/Person/" + Director.getInstance().getUser().getId()+ "/Jewel.txt");
		return appendData(str, file);
	}

	/**
	 * 追加数据处理函数
	 *
	 * @param str
	 * @param file
	 * @return
	 */
	private boolean appendData(String str, File file) {

		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			//将写文件指针移到文件尾。
			raf.seek(raf.length());
			raf.writeBytes(str + "\n");
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
