package com.jaoafa.jaosuperachievement2.lib;

public enum Achievement {
    FIRSTJAO(1, "はじめてのjao", "jao鯖に初めて入る", false),
    FIRSTIMPATIENCE(2, "はじめてのもどかしさ", "「.」と発言する", false),
    FIRSTSPEAKJAOAFA(3, "はじめてのjao afa", "「jao」「afa」と発言する", false),
    FIRSTSPEAKJAOJAO(4, "はじめてのjaojao", "「jaojao」と発言してから退出する", false),
    SPEAKONLYAFA(5, "お前はおはようございますをございますというのか", "jaoを言わずafaと言ったら怒られる", true),
    SPEAKJAOTAN(6, "あのお方", "jaotanの名を言う", false),
    ENCOUNTERJAOTAN(7, "神との遭遇", "jaotanに遭遇する", true),
    FIRSTX4Z(8, "砂利を感じに", "鯖管と時間を共にする", false),
    FORESTBEAR(9, "もりのくまさん", "くまのアタマをかぶる", false),
    WHOISTHEBEAR(10, "くまさんはだれ", "くまになりきる", true),
    BOTCH(11, "BOTCH", "1人になる", true),
    FIRSTSERVERLOGIN(12, "いちばんのり！", "鯖がリスタートしてから一番最初にログインする", false),
    ARUARUHELL(13, "アルアル地獄", ".tと発言する", true),
    FIRSTMIMICRY(14, "はじめてのものまね", "直前に送られたメッセージと同じメッセージを送る", false),
    VERYFREETIMEPERSON(15, "とてもお暇な方", "もっと他にやることないの?", true),
    CHANGETIME(16, "時間操作の神", "Minecraftサーバ内の時間を変更する", false),
    EGGCOVERED(17, "たまごまみれ", "X9Zのあたまを被る", false),
    SPORESCOVERED(18, "胞子まみれ", "Hirotaisou2012のあたまを被る", false),
    BECOME3YEARSOLD(19, "3歳になりきる", "MinHero_expのあたまを被る", false),
    MASK(20, "MASK", "kohonayoshiのあたまを被る", false),
    NOODLEFACTORY(21, "製麺所", "Nudongeのあたまを被る", false),
    FIRSTJAIL(22, "はじめてのjail", "jailされる", false), // MyMaid4で実装
    DRUGADDICTION(23, "薬物依存", "jaoiumでjailされる", false), // MyMaid4で実装
    NOTIMEFORSLEEP(24, "寝てる暇などない", "ベッドで寝る", false),
    FIRSTDED(25, "ded", "「jaojao」を発言せずに退出する", false),
    HEREISHOUSE(26, "ここがおうち", "/sethomeでhomeを設定する", false), // MyMaid4で実装
    AREYOUOKEYATTHISTIME(27, "こんな時間に大丈夫！？", "00:00 ～ 03:00にログインする", true),
    BEWAREOFBEARS(28, "熊出没注意", "mine_book000と時間を共にする", false),
    MUSHROOMPLANTATION(29, "きのこ栽培工場", "Hirotaisou2012と時間を共にする", false),
    WELCOMETOPOULTRYFARM(30, "養鶏場へようこそ", "X9Zと時間を共にする", false),
    BACKSOON(31, "すぐ戻って来ます", "/afkをするか数分放置する", true), // AwayFromKeyboardで実装
    AWAYFORWHILE(32, "しばらく席を外しております", "afk状態のまま5分が経過する", true), // AwayFromKeyboardで実装
    DISAPPEARANCE(33, "失踪。", "afk状態のまま15分が経過する", true), // AwayFromKeyboardで実装
    FRAUD(34, "詐欺", "jaojaoもしくはj2と言って1分経つ", false),
    FRUSTRATINGTYPO(35, "もどかしい誤字", ".ではなく,と発言する", false),
    WEREYOUTHERE(36, "あなた居たの？", "afk状態で退出する", true), // AwayFromKeyboardで実装
    NAUGHTYBOY(37, "わんぱく少年", "両手に雪玉をもつ", false),
    RIP(38, "R.I.P.", "安らかに眠れ", false),
    YOUAREJK(39, "JKじゃん", "「ぴえん」と発言する", false),
    SOHOT(40, "あちゅい", "ものすごい熱気をまとう。", true), // AwayFromKeyboardで実装
    SILENCE(41, "無言の極み", "サーバに参加してから1分間何も喋らない", true),
    WHEREABOUTSUNKNOWN(42, "消息不明", "サーバに参加してから5分間放置", true),
    UNDERGROUNDPEOPLE(43, "そうだ、地底人になろう", "奈落に落ちて死ぬ", false),
    FIRSTJAOX(44, "感謝の極み", "「jaox」と発言する", true),
    GENERALPASSING(45, "一般通過", "ログインして10秒以内にサーバーを退出する", false),
    EARLYBIRDCATCHESWORM(46, "早起きは3jaoぐらい得", "06:00～09:00にサーバにログインする。", true),
    CANTHIDESURPRISES(47, "驚きを隠せないようです", "maj?と発言する", false),
    WHOAREYOU(48, "どちら様ですか？", "「Jao」と\"j\"を大文字にして発言する", false),
    NEWSTEP(49, "新たな一歩", "爆新地の外に出る", false), // Bakushinchiで実装
    EGGANDKETCHUP(50, "たまごとケチャップの合わせ技", "X9Z,mine_book000と時間をともにする", true),
    GRASS(51, "大草原不可避ww", "チャット欄に草を生やす", false),
    WELCOMEBACK(52, "おかえりなさいませ", "「rejao afa」と発言する", true),
    MYHEAD(53, "ぼくのあたま！", "自分の頭を召喚する。", true),
    EARLYSHAREHOLDER(54, "筆頭株主", "誰よりも早くjao鯖に投票する", true), // MyMaid4で実装
    EXPECTEDMEMBER(55, "期待の新人", "jao鯖に投票する", false), // MyMaid4で実装
    STABLESHAREHOLDER(56, "安定株主", "jao鯖に10回投票する", false), // MyMaid4で実装
    MAJORSHAREHOLDER(57, "大株主", "jao鯖に100回投票する", false), // MyMaid4で実装
    LEGENDARYSHAREHOLDER(58, "伝説の株主", "jao鯖に1000回投票する", true), // MyMaid4で実装
    VIPPER(59, "VIPPERな俺", "VIPになる", true), // MyMaid4で実装
    CONTINUATIONLOGINS(60, "継続は力なり", "jao鯖に1週間連続でログインする", true),
    ISTHEREANYONE(61, "誰かいるかな？", "鯖茶から/listコマンドを実行する", true), // MyMaid4で実装
    NORMALJAOIST(62, "ふつうのjaoist", "サーバのログイン時間が1時間を超える", false),
    SINCERITYJAOIST(63, "まことのjaoist", "サーバのログイン時間が1日を超える", false),
    SUPERJAOIST(64, "スーパーjaoist", "サーバのログイン時間が1週間を超える", false),
    CHARISMAJAOIST(65, "カリスマjaoist", "サーバのログイン時間が1ヶ月を超える", false),
    FOREVERJAOEST(66, "えいえんのjaoist", "サーバのログイン時間が100日を超える", false),
    HOTAFK(67, "あったかい", "火炎耐性が付与された状態でafk状態になる", false), // MyMaid4で実装
    SPAMMER(68, "スパマーじゃん", "同じことを二回連続で発言する", false),
    JAILBREAKER(69, "脱獄者だ！", "jailされた状態で退出する", true), // MyMaid4で実装
    IDK(70, "よくわからん", "「？。？」と発言する", false),
    BUILDER(71, "建築家", "建築をする", false),
    MUSHROOMSGROWINGONGRAVEL(72, "砂利の上に生えたきのこ", "X4Z,Hirotaisou2012と時間をともにする", false),
    XNZ(73, "XnZ", "Admin,ModeratorのXnZと時間を共にする", false),
    EVERYONEFUN(74, "皆いると楽しい", "10人以上といっしょに遊ぶ", false),
    HAI(75, "はい。", "ログインしてから1分以内に「jao」とだけ言い、「afa」を言わない。", false),
    UNFORGIVABLEGREETINGS(76, "それは許されない挨拶です。", "「jao afa」と発言する。", false),
    FIRSTSPEAKHAI(77, "逆jao語", "誤字を誤字する。", true),
    EXPECTEDWRITER(78, "期待の作家", "本を書いて署名する", false),
    NUCLEARTEST(79, "核実験", "TNTを所持する", true),
    CAPSLOCK(80, "CapsLockついてますよ", "「JAO」か「AFA」を発言する", false),
    EVERYTHINGBEGIN(81, "すべてのはじまり", "誤字からスタート", true);

    int id;
    String title;
    String description;
    boolean hidden;

    Achievement(int id, String title, String description, boolean hidden){
        this.id = id;
        this.title = title;
        this.description = description;
        this.hidden = hidden;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHidden() {
        return hidden;
    }
}
