using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System.Linq;
using System;

public class ProcessingSQL
{
    // DB名を指定して接続
    SqliteDatabase sqlDB = new SqliteDatabase("namebattler.db");

    private DateTime TodayNow;

    /// <summary>
    /// SELECT文：キャラクターの一覧を取得する
    /// </summary>
    /// <returns>dataTable : 取得したDB</returns>
    public DataTable SelectSQL(int row)
    {
        // ノード内に表示するテキストを検索する
        string query = $"select * from characters limit 1 offset {row}";
        DataTable dataTable = sqlDB.ExecuteQuery(query);

        return dataTable;
    }

    public DataTable SelectSQL(string name)
    {
        string query = $"select * from characters where name ='{name}'";
        DataTable dataTable = sqlDB.ExecuteQuery(query);

        return dataTable;

    }

    /// <summary>
    /// INSERT文：キャラクターを追加する
    /// </summary>
    /// <returns></returns>
    public void InsertSQL(string name, string selectedLabel)
    {
        Player player = null;
        int job = 0;
        int hp = 0;
        int mp = 0;
        int str = 0;
        int def = 0;
        int agi = 0;
        int luck = 0;
        string create_at = "";


        switch (selectedLabel)
        {
            case "戦士":
                job = 0;
                player = new Fighter(name);
                hp = player.GetHP();
                mp = player.GetMP();
                str = player.GetSTR();
                def = player.GetDEF();
                agi = player.GetAGI();
                luck = player.GetLUCK();
                Debug.Log("戦士を作成しました");
                break;
            case "魔法使い":
                job = 1;
                player = new Wizard(name);
                hp = player.GetHP();
                mp = player.GetMP();
                str = player.GetSTR();
                def = player.GetDEF();
                agi = player.GetAGI();
                luck = player.GetLUCK();
                Debug.Log("魔法使いを作成しました");
                break;
            case "僧侶":
                job = 2;
                player = new Priest(name);
                hp = player.GetHP();
                mp = player.GetMP();
                str = player.GetSTR();
                def = player.GetDEF();
                agi = player.GetAGI();
                luck = player.GetLUCK();
                Debug.Log("僧侶を作成しました");
                break;
            case "勇者":
                job = 3;
                player = new Hero(name);
                hp = player.GetHP();
                mp = player.GetMP();
                str = player.GetSTR();
                def = player.GetDEF();
                agi = player.GetAGI();
                luck = player.GetLUCK();
                Debug.Log("勇者を作成しました");
                break;
            default:
                Debug.Log("デフォルト通過");
                break;
        }

        // 作成時間の取得
        TodayNow = DateTime.Now;

        create_at = $"{TodayNow.Year}年{TodayNow.Month}月{TodayNow.Day}日 {TodayNow.Hour}:{TodayNow.Minute}:{TodayNow.Second}";
        Debug.Log(create_at);

        // SQL文の作成
        string query = $"insert into characters values('{name}',{job},{hp},{mp},{str},{def},{agi},{luck},'{create_at}')";
        Debug.Log(query);
        // SQL文実行
        DataTable dataTable = sqlDB.ExecuteQuery(query);

        Debug.Log($"{name}の作成が完了しました");
    }

    /// <summary>
    /// DELETE文：指定したキャラクターをDBから削除する
    /// </summary>
    /// <returns></returns>
    public void DeleteSQL(string name)
    {
        // SQL文の作成
        string query = string.Format("delete from characters where name = '{0}'", name);
        // SQL文実行
        DataTable dataTable = sqlDB.ExecuteQuery(query);

        // 削除に成功したかどうか
        Debug.Log(name + "の削除に成功");

    }

    /// <summary>
    /// DBに登録されたキャラクター数を取得する
    /// </summary>
    /// <returns>count : キャラクター数</returns>
    public int CountSQL()
    {
        int count = 0;

        // SQL文の作成
        string query = "select count(*) as count from characters";

        // SQL文実行
        DataTable dataTable = sqlDB.ExecuteQuery(query);

        // 行数を求める
        foreach (DataRow dr in dataTable.Rows)
        {
            count = (int)dr["count"];
        }

        return count;
    }
}
