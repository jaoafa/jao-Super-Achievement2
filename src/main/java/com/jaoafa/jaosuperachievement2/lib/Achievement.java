package com.jaoafa.jaosuperachievement2.lib;

import java.util.Arrays;

public enum Achievement {
    /**
     * No.1 はじめてのjao
     */
    FIRSTJAO(1, "はじめてのjao", "jao鯖に初めて入る", false),
    /**
     * No.2 はじめてのもどかしさ
     */
    FIRSTIMPATIENCE(2, "はじめてのもどかしさ", "「.」と発言する", false),
    /**
     * No.3 はじめてのjao afa
     */
    FIRSTSPEAKJAOAFA(3, "はじめてのjao afa", "「jao」「afa」と発言する", false),
    /**
     * No.4 はじめてのjaojao
     */
    FIRSTSPEAKJAOJAO(4, "はじめてのjaojao", "「jaojao」と発言してから退出する", false),
    /**
     * No.5 お前はおはようございますをございますというのか
     */
    SPEAKONLYAFA(5, "お前はおはようございますをございますというのか", "jaoを言わずafaと言ったら怒られる", true),
    /**
     * No.6 あのお方
     */
    SPEAKJAOTAN(6, "あのお方", "jaotanの名を言う", false),
    /**
     * No.7 神との遭遇
     */
    ENCOUNTERJAOTAN(7, "神との遭遇", "jaotanに遭遇する", true),
    /**
     * No.8 砂利を感じに
     */
    FIRSTX4Z(8, "砂利を感じに", "鯖管と時間を共にする", false),
    /**
     * No.9 もりのくまさん
     */
    FORESTBEAR(9, "もりのくまさん", "くまのアタマをかぶる", false),
    /**
     * No.10 くまさんはだれ
     */
    WHOISTHEBEAR(10, "くまさんはだれ", "くまになりきる", true),
    /**
     * No.11 BOTCH
     */
    BOTCH(11, "BOTCH", "1人になる", true),
    /**
     * No.12 いちばんのり！
     */
    FIRSTSERVERLOGIN(12, "いちばんのり！", "鯖がリスタートしてから一番最初にログインする", false),
    /**
     * No.13 アルアル地獄
     */
    ARUARUHELL(13, "アルアル地獄", ".tと発言する", true),
    /**
     * No.14 はじめてのものまね
     */
    FIRSTMIMICRY(14, "はじめてのものまね", "直前に送られたメッセージと同じメッセージを送る", false),
    /**
     * No.15 とてもお暇な方
     */
    VERYFREETIMEPERSON(15, "とてもお暇な方", "もっと他にやることないの?", true),
    /**
     * No.16 時間操作の神
     */
    CHANGETIME(16, "時間操作の神", "Minecraftサーバ内の時間を変更する", false),
    /**
     * No.17 たまごまみれ
     */
    EGGCOVERED(17, "たまごまみれ", "X9Zのあたまを被る", false),
    /**
     * No.18 胞子まみれ
     */
    SPORESCOVERED(18, "胞子まみれ", "Hirotaisou2012のあたまを被る", false),
    /**
     * No.19 3歳になりきる
     */
    BECOME3YEARSOLD(19, "3歳になりきる", "MinHero_expのあたまを被る", false),
    /**
     * No.20 MASK
     */
    MASK(20, "MASK", "kohonayoshiのあたまを被る", false),
    /**
     * No.21 製麺所
     */
    NOODLEFACTORY(21, "製麺所", "Nudongeのあたまを被る", false),
    /**
     * No.22 はじめてのjail
     */
    FIRSTJAIL(22, "はじめてのjail", "jailされる", false), // MyMaid4で実装
    /**
     * No.23 薬物依存
     */
    DRUGADDICTION(23, "薬物依存", "jaoiumでjailされる", false), // MyMaid4で実装
    /**
     * No.24 寝てる暇などない
     */
    NOTIMEFORSLEEP(24, "寝てる暇などない", "ベッドで寝る", false),
    /**
     * No.25 ded
     */
    FIRSTDED(25, "ded", "「jaojao」を発言せずに退出する", false),
    /**
     * No.26 ここがおうち
     */
    HEREISHOUSE(26, "ここがおうち", "/sethomeでhomeを設定する", false), // MyMaid4で実装
    /**
     * No.27 こんな時間に大丈夫！？
     */
    AREYOUOKEYATTHISTIME(27, "こんな時間に大丈夫！？", "00:00 ～ 03:00にログインする", true),
    /**
     * No.28 熊出没注意
     */
    BEWAREOFBEARS(28, "熊出没注意", "mine_book000と時間を共にする", false),
    /**
     * No.29 きのこ栽培工場
     */
    MUSHROOMPLANTATION(29, "きのこ栽培工場", "Hirotaisou2012と時間を共にする", false),
    /**
     * No.30 養鶏場へようこそ
     */
    WELCOMETOPOULTRYFARM(30, "養鶏場へようこそ", "X9Zと時間を共にする", false),
    /**
     * No.31 すぐ戻って来ます
     */
    BACKSOON(31, "すぐ戻って来ます", "/afkをするか数分放置する", true), // AwayFromKeyboardで実装
    /**
     * No.32 しばらく席を外しております
     */
    AWAYFORWHILE(32, "しばらく席を外しております", "afk状態のまま5分が経過する", true), // AwayFromKeyboardで実装
    /**
     * No.33 失踪。
     */
    DISAPPEARANCE(33, "失踪。", "afk状態のまま15分が経過する", true), // AwayFromKeyboardで実装
    /**
     * No.34 詐欺
     */
    FRAUD(34, "詐欺", "jaojaoもしくはj2と言って1分経つ", false),
    /**
     * No.35 もどかしい誤字
     */
    FRUSTRATINGTYPO(35, "もどかしい誤字", ".ではなく,と発言する", false),
    /**
     * No.36 あなた居たの？
     */
    WEREYOUTHERE(36, "あなた居たの？", "afk状態で退出する", true), // AwayFromKeyboardで実装
    /**
     * No.37 わんぱく少年
     */
    NAUGHTYBOY(37, "わんぱく少年", "両手に雪玉をもつ", false),
    /**
     * No.38 R.I.P.
     */
    RIP(38, "R.I.P.", "安らかに眠れ", false),
    /**
     * No.39 JKじゃん
     */
    YOUAREJK(39, "JKじゃん", "「ぴえん」と発言する", false),
    /**
     * No.40 あちゅい
     */
    SOHOT(40, "あちゅい", "ものすごい熱気をまとう。", true), // AwayFromKeyboardで実装
    /**
     * No.41 無言の極み
     */
    SILENCE(41, "無言の極み", "サーバに参加してから1分間何も喋らない", true),
    /**
     * No.42 消息不明
     */
    WHEREABOUTSUNKNOWN(42, "消息不明", "サーバに参加してから5分間放置", true),
    /**
     * No.43 そうだ、地底人になろう
     */
    UNDERGROUNDPEOPLE(43, "そうだ、地底人になろう", "奈落に落ちて死ぬ", false),
    /**
     * No.44 感謝の極み
     */
    FIRSTJAOX(44, "感謝の極み", "「jaox」と発言する", true),
    /**
     * No.45 一般通過
     */
    GENERALPASSING(45, "一般通過", "ログインして10秒以内にサーバーを退出する", false),
    /**
     * No.46 早起きは3jaoぐらい得
     */
    EARLYBIRDCATCHESWORM(46, "早起きは3jaoぐらい得", "06:00～09:00にサーバにログインする。", true),
    /**
     * No.47 驚きを隠せないようです
     */
    CANTHIDESURPRISES(47, "驚きを隠せないようです", "maj?と発言する", false),
    /**
     * No.48 どちら様ですか？
     */
    WHOAREYOU(48, "どちら様ですか？", "「Jao」と\"j\"を大文字にして発言する", false),
    /**
     * No.49 新たな一歩
     */
    NEWSTEP(49, "新たな一歩", "中央市の外に出る", false), // ChuoCityで実装
    /**
     * No.50 たまごとケチャップの合わせ技
     */
    EGGANDKETCHUP(50, "たまごとケチャップの合わせ技", "X9Z,mine_book000と時間をともにする", true),
    /**
     * No.51 大草原不可避ww
     */
    GRASS(51, "大草原不可避ww", "チャット欄に草を生やす", false),
    /**
     * No.52 おかえりなさいませ
     */
    WELCOMEBACK(52, "おかえりなさいませ", "「rejao afa」と発言する", true),
    /**
     * No.53 ぼくのあたま！
     */
    MYHEAD(53, "ぼくのあたま！", "自分の頭を召喚する。", true),
    /**
     * No.54 筆頭株主
     */
    EARLYSHAREHOLDER(54, "筆頭株主", "誰よりも早くjao鯖に投票する", true), // MyMaid4で実装
    /**
     * No.55 期待の新人
     */
    EXPECTEDMEMBER(55, "期待の新人", "jao鯖に投票する", false), // MyMaid4で実装
    /**
     * No.56 安定株主
     */
    STABLESHAREHOLDER(56, "安定株主", "jao鯖に10回投票する", false), // MyMaid4で実装
    /**
     * No.57 大株主
     */
    MAJORSHAREHOLDER(57, "大株主", "jao鯖に100回投票する", false), // MyMaid4で実装
    /**
     * No.58 伝説の株主
     */
    LEGENDARYSHAREHOLDER(58, "伝説の株主", "jao鯖に1000回投票する", true), // MyMaid4で実装
    /**
     * No.59 VIPPERな俺
     */
    VIPPER(59, "VIPPERな俺", "VIPになる", true), // MyMaid4で実装
    /**
     * No.60 継続は力なり
     */
    CONTINUATIONLOGINS(60, "継続は力なり", "jao鯖に1週間連続でログインする", true),
    /**
     * No.61 誰かいるかな？
     */
    ISTHEREANYONE(61, "誰かいるかな？", "鯖茶から/listコマンドを実行する", true), // MyMaid4で実装
    /**
     * No.62 ふつうのjaoist
     */
    NORMALJAOIST(62, "ふつうのjaoist", "サーバのログイン時間が1時間を超える", false),
    /**
     * No.63 まことのjaoist
     */
    SINCERITYJAOIST(63, "まことのjaoist", "サーバのログイン時間が1日を超える", false),
    /**
     * No.64 スーパーjaoist
     */
    SUPERJAOIST(64, "スーパーjaoist", "サーバのログイン時間が1週間を超える", false),
    /**
     * No.65 カリスマjaoist
     */
    CHARISMAJAOIST(65, "カリスマjaoist", "サーバのログイン時間が1ヶ月を超える", false),
    /**
     * No.66 えいえんのjaoist
     */
    FOREVERJAOEST(66, "えいえんのjaoist", "サーバのログイン時間が100日を超える", false),
    /**
     * No.67 あったかい
     */
    HOTAFK(67, "あったかい", "火炎耐性が付与された状態でafk状態になる", false), // MyMaid4で実装
    /**
     * No.68 スパマーじゃん
     */
    SPAMMER(68, "スパマーじゃん", "同じことを二回連続で発言する", false),
    /**
     * No.69 脱獄者だ！
     */
    JAILBREAKER(69, "脱獄者だ！", "jailされた状態で退出する", true), // MyMaid4で実装
    /**
     * No.70 よくわからん
     */
    IDK(70, "よくわからん", "「？。？」と発言する", false),
    /**
     * No.71 建築家
     */
    BUILDER(71, "建築家", "建築をする", false),
    /**
     * No.72 砂利の上に生えたきのこ
     */
    MUSHROOMSGROWINGONGRAVEL(72, "砂利の上に生えたきのこ", "X4Z,Hirotaisou2012と時間をともにする", false),
    /**
     * No.73 XnZ
     */
    XNZ(73, "XnZ", "Admin,ModeratorのXnZと時間を共にする", false),
    /**
     * No.74 皆いると楽しい
     */
    EVERYONEFUN(74, "皆いると楽しい", "10人以上といっしょに遊ぶ", false),
    /**
     * No.75 はい。
     */
    HAI(75, "はい。", "ログインしてから1分以内に「jao」とだけ言い、「afa」を言わない。", false),
    /**
     * No.76 それは許されない挨拶です。
     */
    UNFORGIVABLEGREETINGS(76, "それは許されない挨拶です。", "「jao afa」と発言する。", false),
    /**
     * No.77 逆jao語
     */
    FIRSTSPEAKHAI(77, "逆jao語", "誤字を誤字する。", true),
    /**
     * No.78 期待の作家
     */
    EXPECTEDWRITER(78, "期待の作家", "本を書いて署名する", false),
    /**
     * No.79 核実験
     */
    NUCLEARTEST(79, "核実験", "TNTを所持する", true),
    /**
     * No.80 CapsLockついてますよ
     */
    CAPSLOCK(80, "CapsLockついてますよ", "「JAO」か「AFA」を発言する", false),
    /**
     * No.81 すべてのはじまり
     */
    EVERYTHINGBEGIN(81, "すべてのはじまり", "誤字からスタート", true);

    final int id;
    final String title;
    final String description;
    final boolean hidden;

    Achievement(int id, String title, String description, boolean hidden) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hidden = hidden;
    }

    /**
     * Idに合致する実績を返します
     *
     * @param id 実績Id
     * @return 実績Idに合致する実績 (なければ NULL)
     */
    public static Achievement fromId(int id) {
        return Arrays.stream(values())
            .filter(achievement -> achievement.id == id)
            .findFirst()
            .orElse(null);
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
