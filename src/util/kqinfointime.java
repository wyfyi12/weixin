package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dao.localdao;

public class kqinfointime {
	public static HashMap<String, HashMap<String, String>> getkqinfo(String date) throws Exception{
		HashMap<String, HashMap<String, String>> kqinfo=new HashMap<>();
		localdao.getConnection();
		HashSet<String> kq=localdao.queryuserkqbytime(date);
		System.out.println(kq);
		for(String alias: kq){
			System.out.println(alias+","+date);
			HashMap<String, String> kqt=new HashMap<>();
			ArrayList<HashMap<String, String>> kqlist=localdao.querykqbydate(date, alias);
			System.out.println(kqlist);
			int fail=0;
			for(int i=0;i<kqlist.size();i++){
				if(kqlist.get(i).get("status").equals("fail")){
					fail++;
				}
			}
			kqt.put("kqno", kqlist.size()+"");
			kqt.put("kqfail", fail+"");
			System.out.println(kqt);
			kqinfo.put(alias, kqt);
		}
		return kqinfo;
	}
}
