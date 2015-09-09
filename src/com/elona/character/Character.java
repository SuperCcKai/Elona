/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elona.character;

import com.elona.constants.Attribute;
import com.elona.constants.StatusAilment;
import java.util.ArrayList;
import javafx.scene.image.WritableImage;

public class Character {
    
    /*
                  Cdata
    */
    
    int state; //キャラクターの状態 Const→State.java
    int cX; //マップ上のX座標
    int cY; //マップ上のY座標
    int newX; //判定用移動先のX座標
    int newY; //判定用移動先のY座標
    long respawnTime; //復活予定時刻(値の扱いは異なる)
    WritableImage charaChip;//キャラチップ(値の扱いは異なる)
    int sex; //性別 Const→Sex.java
    int relation; //関係 Const→Relation.java
    long speedSum; //ターンコスト用の速度合算
    long speed; //現在速度
    // Item aiItem //次に使用するアイテム(値の扱いは異なる)
    WritableImage portrait;//顔グラフィック識別番号(値の扱いは異なる)
    int interest; //興味度 cdata014
    long interestRenewTime; //興味度回復時刻(値の扱いは異なる)
    int personality; //性格
    long impression; // 友好度 Const→Impression.java
    int tone; //口調
    long height; //身長
    long weight; //体重
    long bornTime; //生まれた時刻 cdata021(値の扱いは異なる)
    long hunger; //満腹度
    int quality; //ユニット品質 Const→Quality.java
    long turn; //累積行動回数
    int Id; //キャラ識別番号
    int fov; //視界直径
    Character target; //敵対中ユニット(値の扱いは異なる)
    long gold; //所持金貨
    long plat; //所持プラチナ硬貨
    int attackStyle; //装備状態 Const→AttackStyle
    int meleeStyle; //物理攻撃のテキスト種別 Const→MeeleStyle
    long fame; //名声
    long exp; //現在経験値
    long expToNext; //レベルアップに必要な経験値
    int timeScale; //現在の速度補正(重量オーバー、疲労などによる影響、百分率)
    long level; //経験レベル
    long speedFix; //次のターンの速度補正
    long skillPoint; //スキルボーナス
    long earnedSP; //累積獲得スキルボーナス(格納されているだけで使ってない模様)
    long invWeight; //バックパック内のアイテムの重さの合計
    long carryLimit; //持てる重さ
    int burden; //所持アイテム重量状態 Const→Burden
    long levelOrg; //最大到達経験レベル
    int karma; //カルマ
    long HP,MHP; //現在HP,最大HP
    long SP,MSP; //現在SP,最大SP
    long MP,MMP; //現在MP,最大MP
    long drainHP; //装備による地獄属性攻撃の回復基本値 cdata060
    int god; //信仰している神 Const→God.java
    long piety; //信仰ポイント
    long pray; //祈りポイント
    long eqWeight; //装備品合計重量
    int castStyle; //詠唱のテキスト種別 Const→CastStyle.java
    int vopalChance; //完全貫通率
    int critChance; //クリティカルヒット率
    int bodySpdFix; //部位数による速度補正率
    int relationOrg; //元の関係(インコグニート等で使用)
    long PV,DV; //装備品PV合計,装備品DV合計
    long ATK; //防具命中修正合計
    long DMG; //防具ダメージ修正合計
    long PVFix,DVFix; //PV補正値,DV補正値
    
    int barrierElement;
    long barrierPower;
    //被ダメージ時反撃属性? 元はcBarrierだったものを2つに分化
    
    int emoIcon; //使用interfaceチップNo.
    int area; //滞在中マップNo
    int areaLv; //滞在中マップ階層
    
    int questNpc; //依頼主になりうるユニットの場合、依頼主番号(Elona Javaでは使わない可能性あり)
    int dir; //ユニットの向き Const→Dir.java
    
    long hirePeriodTime; //冒険者の雇用期限時刻
    int friendship; //雇用回数
    
    long insane; //狂気度
    int curse; //装備呪い合計強度
    
    int extraMelee; //合計追加打撃発生率
    int extraShoot; //合計追加射撃発生率
    int resDamage; //装備物理軽減合計強度
    int immuneDamege; //合計被ダメ無効発生率
    int reflectDamage; //装備与切り傷合計強度
    
    int vomit; //吐いた回数 睡眠時に減少
    
    /*部位情報*/
    /*部位ごとに装備しているアイテム*/
    
    int rowAct; //行動中の継続行動識別子 Const→Action.java
    int rowActPeriod; //継続行動残りターン
    // Item usingItem; //継続行動使用中アイテムNo(値の扱いは異なる)
    boolean rowActWarn; //不明、たぶん継続行動中の警告表示フラグ？
    
    long performScore; //演奏中の成功値
    long performGold; //演奏で手に入ったおひねり(演奏中の途中経過)
    
    int cRole; //ユニットイベント
    long shopLv; //お店の規模
    
    Character rowActTc; //継続行動の対象キャラ
    
    /*
    cdata153 在庫保存用一時ファイル番号
    このクラスに一気に保存もできるためいらない可能性高し
    */
    long restockTime; //次回入荷予定時刻(値の扱いは異なる)
    
    /* cNPCはMODとしての導入となるため不要 */
    
    int race; //種族 Const→Race.java;
    int orgX; //標準位置のX座標
    int orgY; //標準位置のY座標
    
    
    /*
    AI関連についてはデータ構造からいじるかもなので後回し
    cdata(200,ユニットNo.)	死因対象のユニットNo
    cdata(201,ユニットNo.)	興奮度？(100以下で%txtCalm,101以上で%txtAggro)
    cdata(202,ユニットNo.)	移動AIというか非戦闘時AI。cNPCのaiCalm.に対応している
    1:aiRoam (ランダム)
    2:aiDull (標準位置の周りで動く。市民とか)
    3:aiStand (動かない)
    4:aiFollow (プレイヤーを追跡。標準では『グウェン』のみ)
    5:特殊(基本的には1と同じ、たまに特殊な行動(演奏、プレイヤーへ塩投擲、NPC同士の気持ち良いこと)をする。
    cNPCには設定できない)
    cdata(203,ユニットNo.)	移動用変数？
    cdata(205,ユニットNo.)	移動目標とするX座標
    cdata(206,ユニットNo.)	移動目標とするY座標

    cdata(207,ユニットNo.)	移動確率
    cdata(208,ユニットNo.)	最適距離
    cdata(209,ユニットNo.)	特殊行動率
    cdata(211,ユニットNo.)	回復行動
    cdata(212,ユニットNo.)	設定行動数
    cdata(215,ユニットNo.)	基本行動1～5
    ～
    cdata(219,ユニットNo.)
    cdata(220,ユニットNo.)	特殊行動1～5
    ～
    cdata(224,ユニットNo.)*/
    
    int element; //素手攻撃属性
    long elementPower; //素手攻撃属性パワー
    //cElement 素手攻撃属性とパワーを分割
    
    int attbFix[] = new int[Attribute.tailAttb]; //主能力補正値(変数を統合)
    int statusAilments[] = new int[StatusAilment.tailStatusAilment]; //状態異常残ターン(変数を統合)
    
    /* cdata270～cdata279、食事効果？ 後回し */
    /* 呪文の扱い未定、後回し
        cdata(280,ユニットNo.)	呪文などによるbuff効果(種別、効果値、残りターンの3要素×16個分)
        ～	
        cdata(327,ユニットNo.)*/
    
    boolean floating; //あなたを浮遊させる
    boolean invisi; //不可視フラグ
    boolean seeInvisi; //透明な存在を見ることを可能にする
    boolean resStatusAilments[] = new boolean[StatusAilment.tailStatusAilment]; //～を無効にする(変数を統合)
    boolean eater; //腐ったものを難なく消化させる
    boolean resSteal; //アイテムを盗まれなくする
    boolean incognito; //変装中フラグ
    boolean dropGold; //金貨ドロップフラグ
    boolean suicide; //自爆生物フラグ
    boolean deathMaster; //死の宣告持ちフラグ
    boolean rapidMagic; //連続魔法持ちフラグ
    boolean layHand; //レイハンド持ちフラグ
    boolean horse; //適正騎乗生物フラグ
    boolean split; //分裂生物フラグ
    //boolean encCurse; //自動発動型エンチャント装備中フラグ　要変更？
    boolean noHorse; //非適正騎乗生物フラグ(非力すぎる)
    boolean resEle; // 元素耐性フラグ?被ダメージが属性ダメージだった場合にそれが魔法以外の属性であれば無効化する
    boolean split2; //分裂生物フラグ2?(キューブタイプ)
    boolean metal; //金属生物フラグ?
    boolean cureBleeding; //出血を抑えるフラグ
    boolean powerBash; //フィート:盾殴り持ちフラグ バッシュの発生率が5%アップ
    boolean immuneMine; //地雷無効フラグ?
    boolean temper; //怒り可能フラグ?
    
    boolean livestock; //牧場で放牧中フラグ
    boolean marry; //結婚フラグ?
    boolean makeLove; //遺伝子作成済みフラグ?
    boolean bodyguard; //護衛依頼の護衛対象フラグ?
    boolean summoned; //ゲスト、終末で発生した生物フラグ
    boolean shutup; //黙らせるフラグ(?)
    boolean scope; //聴診器を使われているフラグ
    boolean pcc; //pccフラグ
    boolean leash; //紐でくくってあるフラグ
    boolean hired; //冒険者契約中フラグ
    boolean questTarget; //クエストターゲットフラグ
    boolean guardTemp; //不明、ガード関係？
    boolean suicideSwitch; //自爆スイッチフラグ
    boolean deathCount; //死の宣告フラグ
    boolean layHandCharge; //レイハンド使用可能フラグ
    boolean ride; //騎乗されているフラグ
    boolean precious; //ランダムダンジョンボスフラグ
    boolean haveName; //ランダム名前つきフラグ？
    boolean pregnant; //エイリアン寄生フラグ
    boolean noTarget; //不明、ターゲットにならない？
    boolean contingency; //buff契約フラグ
    boolean refresh; //不明
    boolean eventTalk; //不明
    boolean stoneBlood; //無機物フラグ
    boolean tokenFriend; //不明
    boolean sandBag; //サンドバッグぶら下がりフラグ
    boolean anorexia; //拒食症フラグ
    boolean poisonFlag; //プレイヤーからアイテムを渡されたフラグ?
    //boolean aiSaveMana; //不明、AI関連っぽいので保留
    boolean msgFile; //口調を変えてるフラグ
    boolean noGod; //不明
    boolean festival; //不明
    
    /*cbit(990, ユニットNo.)	言葉を覚えてるフラグ(口調フラグ優先)
    1.16ソースにはない String型で代用効くからいらないかも？ */
    
    /*
            cdatan
    */
    
    String name;
    String aka;
    /* 種族・職業についてはint型で管理するかもなので後回し */
    String teachWord;
    
    /*
            inv
    */
    
    //ArrayList<Item> inventory = new ArrayList<>();
    
    
}
