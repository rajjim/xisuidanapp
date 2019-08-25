package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class cijiyi extends AppCompatActivity {

    Bundle b;
    String[] ci = { "风向标","小刀","药片", "饼干", "胶水", "海藻", "菌类", "苹果","蒸气","小麦","螺帽","指北针", "键盘", "脚印", "钻石", "银币", "仪器","钩线","女性", "餐具", "温室", "水槽", "视力表", "熔岩", "石油", "袋鼠", "加油站","水母", "犄角", "衣袖", "灯塔", "轮椅", "丝绒", "群岛", "插座", "马房", "螺帽", "锐角", "蛋白质", "冰箱", "峭壁", "长剑","说明书","马铃薯","拇指","后备箱","蘑菇", "脚印","烟灰","毛皮","歌剧","企鹅", "嘴唇", "潮汐", "浮标", "花粉", "针灸", "芭着","荆棘", "合同","奴隶","山脉","电脑", "六弦琴", "卡片","乔木","女王","研讨会", "风车","I厂","肌肉","商业","森林","公园", "婚纱", "水银","胡椒", "巫师","重肉","水井","眼镜","红杉","物理", "停车场", "墙","锐角","游戏","假发", "邻居","碳酸", "领带","银行", "说明书", "马铃薯", "拇指","舞蹈", "曲奇","脚印", "咽灰","毛皮", "收音机", "重表", "电梯",
            "飞机","航班","梳子","雨伞","绅士","语言","电影","竖琴","下水道","谜语","字典","会议", "指甲", "麦片","命令", "苹果", "水山","钟楼","火车","网络","香料","酒店","钉耙", "灵柩","吃丐","眼睛","登山","档案","高中","炉灶","西红柿","幻觉","干电池","胚胎","蹦蹦床","酒吧","胶片","笔记本","火鸡","美术馆","鼓","幼儿园","椅子","图钉","骗局","草药","旅行","女儿","秘密","剪刀","奥巴马","锤子","家规","父母","精英","肯德基","饭团","自助餐","豆浆","杏脯","大白菜","樱桃","蜜渍凤梨","热狗","增加","磨砂膏","餐馆","家规","紫菜","迷你裙","椰丝","板条","小狗","水饺", "家规","细节","眼镜", "喇须刀", "大蒜","保险管", "龙眼乾","银杏", "车梨子", "唾花汤", "桂圆","核心", "蜈蚣","牡蛎汤", "青椒","傈鼠","露桃", "土壤","文化","呀膏","电冰箱", "黑猩猩","猴子","苍蝇","面包机","剪刀", "毛虫", "电炉","温柔","替罪羊", "精英","电推子",
            "榨汁机", "鲤鱼","动物", "杏脯", "变色龙","烘干机", "云朵","肉丸","增加","三明治","白头鹰","冰柜","螳邮","迷你裙","熨斗","石斑鱼","河狸","章鱼","家规","避开","海豚","孩子","吹风机","水管","欢迎","冷冻柜","葱油饼","桌子","桂圆","燕雀","煮蛋器","哑天鹅","羚羊","傈鼠","狐狸","会议","咖啡壶","蛋卷", "电冰箱","水壶","蝙蝠","超市","老鹰","芥菜","人类","胭脂","电插座","爱滋病","海狸鼠","稀饭", "小狗", "学堂", "豆浆", "爵士乐", "钉子", "懊恼", "铅笔", "肥皂剧", "试电笔","卡片", "狸懒", "摇滚乐","胶布", "皮蛋", "棉花", "节能灯","葡萄乾", "嘿蠢","粉","柿饼","大虾", "罂粟","棋杷", "茄子", "学校", "矮马","橡皮擦", "餐香", "蝴蝶", "葵花", "高尔夫", "马路", "峥吵", "洋葱", "鲨鱼", "发脾气","王米", "菌类", "脚印", "咽灰","毛皮","总统","天南星","包心菜","箭猪", "惩罚", "虎鲸", "红萝卜", "千鸟", "家","麦片","牛奶","闹钟",
            "起床","衣服","野鸽", "超人", "绿卡", "芹菜","杜鹃","牵花","槟榔", "螳螂","黄鼠狼", "苹果","兼职","蝙蝠", "屁股", "林芝", "绒蒿", "天堂鸟","天空", "清洗","含羞草","金丝雀", "麋鹿", "金盏花", "齶梨", "做饭", "生意", "凤仙花", "女人", "评价", "鸭嘴兽", "读书","家务","呵乐", "雪貂","非洲象","莲花", "食堂","玉龙山","迷迭香", "欧洲", "芥末","香菜", "老板","自恋", "布匹", "大楼", "培根", "银杏","薄荷", "雪碧", "藏红花", "松鸡", "迷你裙", "自助餐","吵丁鱼", "地图", "土多店", "太阳浴","啤酒花", "袋鼠", "影视片", "夏天", "土拨鼠", "石松","叮香","辣椒", "绣球", "普通人", "夜莺", "翻译", "夹克衫", "萝卜", "吗赛克","豆蔻","章鱼","突破","肉桂", "茴香","美洲豹","咖啡","玩具","客车","后备箱", "动物园","游乐场", "烟灰","毛皮","香菜","小刀","婴鹉","山葵","抱怨","培根", "菌类","小草","鹤鹑","零用钱","松鸡","指北针土司",
            "家庭","比基尼","士多店","八分仪","感冒","竹芋","枪乌贼","夏天","水槽","价值观","画眉","蟾蜍","绣球", "加油站","童工","染发","金枪鱼", "莳夢", "轮椅", "眉笔","护手霜", "金额", "肉桂","螺帽", "扬子鳄", "豪华","衣柜", "喀车", "热水", "凤仙花", "发胶", "摩丝", "轮船", "钉子","笑话","板粟", "花朵","蛋卷", "鲱鱼","药片","螃蟹", "奴隶", "品质", "国外","成就","影片", "钮扣", "吉普车", "螃蟹", "田螺", "衣服", "虾米","乌鸦","巧克力", "青椒","水手","榴莲", "血浆", "虾仁","洋葱","商人","白菜","文凭","无花果","活领","旅途","放假","模仿","熄灯","浪费","鳕鱼","针织衫","疟蚊","工作","健康","睡衣","铂金","刀削面","浴衣","珍珠","死亡","山竹","绿藻","运动","状况","功能","仙人掌","橡树","磨砂芭蕾","品质","麻辣面","荔枝","卡丁车","辣椒粉","吉普车","急救车","留留球","拉力赛","披头士","乌鸫", "咖啡","便装","围棋","星星","血浆",
            "呵可","呼啦圈","软水管", "克隆", "咬凭", "虾球","秘密","辣妹","头发", "模仿", "松鼠","电费","帆船","面条", "疟蛟","对话", "变压器", "黑莓","十字架", "刀削面", "清障车","网吧","手套","影碟","绿藻","甲虫", "钢琴","克","粉丝", "白醋", "保龄球", "呀关", "冰淇淋", "白 梦", "飞镖","云杉", "机器", "嘈人街", "平头","甲壳虫", "迪士尼","噢秘", "方便面","太阳镜","仙人掌","毛衣","棋类","修道院","椰油","上网","派对","灯丝","坚果","校长","欣赏","桑椹","追星","妈咪","水糖","灯罩","健康","呀膏","学报","胭脂","润肤露","逃避","旗袍","含义","白醋","篮球","洗发精","蹦跳","眼影刷","棉签","尿布","月亮","因特网","蓝枪鱼","水池","山梨","闪电","海绵","睫毛","噢洲", "拖车","布谷鸟","房子","凤尾蝶","油豆腐","政权","板栗","大雁","旗帜","经验", "糯米饭","相对论","金针","企业","嘿颈鹤", "蛋炒饭", "灵性","吱屑", "竞争者","春卷","教育部",
            "能里", "胸罩", "基督教","股份","天堂鸟","中心","呐部","极乐鸟", "露营", "耶稣","小梳打","化学","数学","狐狸","奶奶","鱈鱼","发夹","教堂","蜂蜜","短吻鳄","蛤蜊","酱油","踢踏舞","炼乳","不倒翁","沙发","人才","蛋卷", "咚瓜", "下坡路", "蟾蜍", "高尔夫", "数据线", "咩驼", "樱桃", "烂果", "医生","移动电话","精髓","看法","刀鱼","蛋黄","蟋蟀", "鸡精","究賁","秃鹰","平安","花蕊","蟠桃","对立","喇蛄", "石榴", "非洲鳄", "金桔", "红枣", "表述","鳄鱼","动物", "荔枝", "起步", "美洲腹蛇", "海龟", "轨道", "蝉", "著作", "饮料", "大黄蜂", "诽谤", "螳螂", "鸬鹚", "蚂蚁","毛巾","态度", "天鹅","军事","排斥","野鸡","死亡","桔子","报春", "工程","哺乳", "神殿", "皮鞋", "蟋蟀","蝴牛","洋槐","山岗","蝗虫","蜈蚣","英国", "海洋", "呗壳", "蝴蝶", "蝎子","执事","白喉雀","鸵鸟", "确度","枪械","长老","蜥蜴","估算","鱿鱼", "地球", "辅助",
            "白色", "预测", "环境", "工程师","肾脏", "男生", "白桦","息崖","交通干线","粮食", "哆样性","税书长","斑马线","生存", "高原", "鸣类","假睫毛","菠萝", "樟树","口红", "紫杉", "小龙虾","遥控器", "大危机","悲观","牡蛎", "眼镜蛇","嗅虫","粮食", "雏鹰", "企鹅","娃娃鱼", "博爱", "呜檀","脊椎", "变色龙","眼镜", "火山", "安全线","斑鸠","哂伯利亚", "蜈蚣", "全球","嚯鸟", "淡水", "冷杉","飞机", "喷气", "飞机","两栖动物", "河床", "气垫","峡谷", "有机体","清真寺","风暴", "扑克牌", "乳液","温度", "标准", "联合国","唱诗班", "哈欢树", "繁衍", "圣像", "蟑螂","乌贼","儿","破冰船","十字架", "巡洋舰", "运动员", "虎鲸","嬉皮士", "泡芙","手掌","黑人","传真","蓝领","奴隶", "百姓","羽毛","山梅花", "加拿大", "布丁", "寝室", "划桨船", "花样", "激光", "调味品", "葡萄柚","芍药","菠菜", "核酸", "调料", "防晒","桅子花","鼹鼠","噱头", "暖意",
            "眼影", "驱逐舰", "高丽", "嘿子","沙士", "热饼", "蝴蝶", "咩芋块", "嘴苣", "生命","士兵", "佛塔","面膜", "土拨限", "芦善汁", "情高", "大象","染发", "水地", "身体乳","辣根","紫坛", "传媒","银杏", "甘蓝","味精","照片", "女郎","着善", "味治露", "蛋糕", "母亲","圣诞耳藤", "打印机","愿望","蒲公英","飞跑", "豆芽", "试管婴儿","曲奇", "豆斓菜","青菜","按骨木", "红枥", "猪排", "鸡胸肉", "硅谷", "嗜鱼蛇", "啾葵", "珍珠", "羽扇豆","咆心菜", "创立", "面奶","蔬菜汁", "缺点", "克隆羊", "电视", "嫩黄瓜", "哈密瓜", "绞肉", "网络", "芜菁", "洋姜", "茶叶", "沙茶酱", "紫杉", "计算机水泡","化妆品", "毛豆", "今天","报考", "保障","金字招牌", "开展", "地位", "培养", "教堂", "爆料", "覆盖面", "深造", "极其", "依赖", "同济","促进","傲气", "学府","大学","味来","体制","并列","微电子", "飞行器","警惕", "知识","班级","复日","暴力","唠叨",
            "炒作", "工科", "热门", "国际关系", "倾尽","全力雄厚","优势", "斑马", "制造业", "在乎", "满意", "薪酬", "稍逊", "势头", "失去","改造","至今","电信","会场","杀手锏", "伴侣","主流","计划", "衰落", "职位", "感化", "劲","齐名", "考古","终结","奴隶","承认","声誉","繁荣","圆周","参考","领域", "无人匹敌", "王牌", "自私", "私企","崭露头角", "抢手", "尝试", "崩溃","加盟","振动", "精英", "强势", "鼠标", "牛辅", "企业界","潜力", "原则性", "牛奶", "公务员","诟病","风格", "内疚", "蜂蜜", "地质", "乖乖女", "触犯", "百依百顺", "饮料", "遥遥领先", "法","数学", "激情", "白开水", "无疑", "类型","面包","哗丽", "取胜", "平淡","沉着","璜金","如意","罗曼蒂克","情调", "逐渐", "客啬鬼", "啡洲","得失","哈作","支出","严肃","税金","麻将", "逢场作戏", "观念","反省","优秀","风筝","谦让","融洽","伪装", "游戏", "薄弱", "社交性", "归属感","明天","病人",
            "大海","胜利","争论", "纸巾","医院","坚果","捆绑","复原","怪兽","早晨","雪糕","谨慎","妥协","高雅","花朵","眼镜","朴实", "质料","温柔","彩纽","腿却","谋求", "消极","现状","身体", "潜意识","维持", "特性","和平能","顺利", "绵羊","心意","周通", "打架","悲观","生病","精神病","弹弓","神经质","直觉", "顾虑","水果","太阳", "小偷","细腻","哗师", "白棋","欣赏", "话题","朴素","嗆土奇","嘿汁","稳重", "快餐","文静","嚯水","石头","图书馆","方便面", "艺术馆","满天星", "人格", "自律", "成熟", "手表","言行","节日","股票","金曲","太阳能","蚁族", "吗铃薯", "好出风头", "货款", "律师", "足迹", "嚶鵡", "人情","经济","魔豆","风暴","槟榔", "极端","威化饼干","戏剧","大门", "白马", "证券", "熊猫","家族","蛋白", "博斗", "精细", "医院","咖菲猫","薯片", "盗版","香港","楼盘", "容易","超市","动能车", "价格", "小清新","药房", "餐厅", "剩饭", "纸巾",
            "黑砖宝", "疯狂", "猪肉", "呵乐", "银行", "功能","淘汰", "蜜饯", "公司", "工作", "和谐号", "节能", "减排", "亚洲", "海绵", "运势", "手机", "歧视", "名片", "灰色", "通心面", "宅男", "恋爱", "火疗", "珠江", "麻辣汤", "零食", "海里版", "盲人", "解说", "负债","奥运","飞机","太空", "椅子", "储蓄","女生","国旗", "货币", "鸦片","备用金","驾校", "民族", "声波", "坟墓", "稳健", "奖状", "黄牛位", "民生", "时代","山峰", "婴儿","扛包团","淘宝", "房间","出息", "幽林", "刷书客","天猫", "惊东", "洞穴", "小长假","贿赂", "学业", "长江", "售楼", "晒黑", "统领", "移动", "会议", "尊老爱幼","试客", "前熬", "证明书","交通", "钞票", "大笑", "投票", "领油", "挖掘", "喇女","降价","钉","吹货","生活","笑脸","检查","定粉", "师范生", "秒杀", "刷牙群", "租密", "贷款","汽车","管家","奴隶","哎滋","大学生", "太阳","石油", "考霸","意味","橡胶","风向标",
            "农油", "课考","树叶","长裙","海藻","插座","经济","乌龙茶","碳酸","螺帽","箱","拼车", "排遣","卫星","银币","拇指","穷人", "抱抱团", "香皂", "温室","毛皮","垃圾箱","车奴","鞋跟", "喉鼠", "胶水","育婴师", "药片", "煎蛋", "灯塔","小麦","小刀","苹果","锐角","吗房", "钻石","菌类", "键盘", "说明书","峭壁", "啊具", "指北钭", "钧鱼", "脚印","后备箱","物理","仪器", "视力表","哈同","饼干", "假发","水槽","竦椒", "卡片","嗓气","说明书","歌剧", "企鹅", "嘴唇","收音机","电梯","花粉","针灸", "芭蕾","梳子", "绅士","奴隶","山脉","电脑","坚琴", "谜语", "乔木","王","研讨会", "指甲", "命令", "肌肉", "商业", "森林", "钟楼", "网络", "水银", "胡椒", "巫师", "钉把","吃丐", "眼镜", "红杉","公园", "档案","帕灶", "锐角", "游戏","重肉", "吁电池", "嘛蹦床", "颁带", "银行", "停车场", "笔记本", "美术馆", "I厂", "沙","居","椅子","骗局", "婚纱", "荆棘",
            "马铃薯", "重表", "航班","水井","六弦琴", "飞机","雨伞", "电影", "墙","锤子", "语言","吓水道", "会议", "碳酸","饭团", "字典","唛片","水山", "拇指","樱桃","苹果","伙车", "酒店", "秘密", "餐馆","香料","灵柩","登山","父母","顺条","眼睛","高中","幻觉","豆浆","眼镜","哂红柿","胚胎","胶片","热狗","银杏","酒吧","火鸡","幼儿园","紫菜","螺蛇","鼓","图钉","旅行","家规","肯德基","替罪羊","精英", "电推子","自助餐","大白菜","动物","杏脯","变色龙","蜜渍凤梨","磨砂膏","肉丸" ,"增加", "三明治","家规", "椰丝", "螳螂","迷你裙","熨斗", "小狗", "细节","章鱼", "家规", "避开", "剃须刀","龙眼乾", "吹风机", "水管","欢迎","车梨子","核心", "桌子","桂圆","燕雀","牡蛎汤","露桃", "羚羊","栗鼠","狐狸","文化","黑猩猩","蛋卷","电冰箱","水壶","剪刀","苍蝇电炉","超市", "胭脂","精英","温柔","榨汁机","电插座","小狗", "杏脯","鲤鱼", "烘干机","学堂","懊恼",
            "增加","云朵" ,"白头鹰","铅笔","狸獭","迷你裙","冰柜","石斑鱼","摇滚乐", "节能灯","家规","河狸","海豚","葡萄乾"," 大虾","保险管","孩子","冷冻柜","罂粟","矮马","桂圆","葱油饼","煮蛋器","橡皮擦", "高尔夫", "栗鼠","哑天鹅", "会议","马路","发脾气", "电冰箱", "咖啡壶","蝙蝠" ,"玉米","毛皮","老鹰","音乐","英语","数学","手机"};
    Button jiyibtok;
    Button cijiyibtstart;
    LinearLayout jiyill;
    int height;
    int level;
    int width;
    String[] randomStrings;
    int zongmiao = 0;
    Timer timer;
    boolean isstop = true;

    Handler mHandler = new Handler()
    {
        public void handleMessage(Message paramAnonymousMessage)
        {
            super.handleMessage(paramAnonymousMessage);
            switch (paramAnonymousMessage.what)
            {
            }
            while(isstop)return;
            zongmiao += 1;
            int s=zongmiao/216000;
            int f=zongmiao/3600;
            int m = cijiyi.this.zongmiao / 60;
            int hm = cijiyi.this.zongmiao;
            cijiyi.this.cijiyibtstart.setText(s%24+":"+f%60+":"+m% 60 + "." + hm %60);
            cijiyi.this.mHandler.sendEmptyMessageDelayed(1, 1);
        }
    };

    public String shuzitomiao(int shuzi){
        String shijian="";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        date.setTime(shuzi);//java里面应该是按毫秒
        shijian=sdf.format(date);
        return shijian;
    }

    public static int adjustFontSize(int paramInt1, int paramInt2)
    {
        if (paramInt1 <= 240) {
            return 13;
        }
        if (paramInt1 <= 320) {
            return 16;
        }
        if (paramInt1 <= 480) {
            return 18;
        }
        if (paramInt1 <= 540) {
            return 21;
        }
        if (paramInt1 <= 800) {
            return 23;
        }
        return 25;
    }


    public String[] getRandomStrings(){
        String[] rets=new String[level];
        Random r = new Random();
        int ciStringSums=this.ci.length;
        for(int i=0;i<level;i++){
            int xiabiao=r.nextInt(ciStringSums);
            rets[i]=ci[xiabiao];
        }
        return rets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cijiyi);
        Display display = getWindowManager().getDefaultDisplay();
        Point localPoint = new Point();
        display.getSize(localPoint);
        this.width = localPoint.x;
        this.height = localPoint.y;
        this.b = getIntent().getExtras();
        jiyill=(LinearLayout)findViewById(R.id.jiyill);
        cijiyibtstart=(Button)findViewById(R.id.jiyibtstart);
        jiyibtok=(Button)findViewById(R.id.jiyibtok);
        String levelstring=b.getString("level").trim();
        switch(levelstring){
            case "5个词":{
                this.level = 5;
                break;
            }
            case "10个词":{
                this.level = 10;
                break;
            }
            case "20个词":{
                this.level = 20;
                break;
            }
            case "50个词":{
                this.level = 50;
                break;
            }
            case "100个词":{
                this.level = 100;
                break;
            }
        }
        this.randomStrings=getRandomStrings();
        cijiyibtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cijiyibtstart.setClickable(false);
                String message="";
                LinearLayout hll=new LinearLayout(cijiyi.this);
                hll.setGravity(Gravity.CENTER);
                hll.setPadding(0, 5, 0, 0);
                hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                hll.setOrientation(LinearLayout.HORIZONTAL);
                hll.setGravity(1);
                for(int ii=0;ii<cijiyi.this.level;ii++){
                    EditText et=new EditText(cijiyi.this);
                    et.setText(randomStrings[ii]);
                    et.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                    et.setLayoutParams(new ViewGroup.LayoutParams(cijiyi.this.width / 5, -2));
                    et.setFocusable(false);
                    et.setInputType(0);
                    et.setPadding(0, 0, 0, 0);
                    et.setGravity(17);
                    et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
                    et.setTextSize(0, cijiyi.this.width / 23);
                    hll.addView(et);

                    if((ii+1)%5==0){
                        jiyill.addView(hll);
                        hll=new LinearLayout(cijiyi.this);
                        hll.setGravity(Gravity.CENTER);
                        hll.setPadding(0, 5, 0, 0);
                        hll.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                        hll.setOrientation(LinearLayout.HORIZONTAL);
                        hll.setGravity(1);
                    }
                }
                isstop = false;
                mHandler.sendEmptyMessage(1);
            }
        });
        jiyibtok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cijiyi.this.isstop = true;
                cijiyi.this.mHandler.sendEmptyMessage(1);
                Intent intent = new Intent(cijiyi.this, cihuiyi.class);
                intent.putExtra("level",cijiyi.this.level);
                intent.putExtra("time", cijiyi.this.zongmiao);
                intent.putExtra("shuzu", cijiyi.this.randomStrings);
                cijiyi.this.startActivity(intent);
                cijiyi.this.finish();
                return;
            }
        });
    }

}
