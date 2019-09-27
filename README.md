# monsterAndHunter

MonsterAndHunter

无聊试着弄个文字随机放置类小游戏


1.首先创建hunter

localhost:8080/hunter/add_hunter
{
    "name": "小高炮",
    "maxLife" : "100",
    "curLife": "100",
    "isLive": "1",
    "maxAttack": "10",
    "minAttack": "2",
    "defend": "1",
    "level": "1",
    "exp": "0",
    "needExp": "100",
    "agile": "1",
    "hideRate":"1"
}

2.根据得到的hunterId进行战斗
localhost:8080/hunter/fight?hunterId=627202435965779968

 
