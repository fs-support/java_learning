using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

/// <summary>
/// 戦闘ログを記録するクラス
/// </summary>
public class LogText
{
    /// <summary>
    /// 戦闘ログを格納するリスト
    /// </summary>
    private static List<string> logMsg = new List<string>();

    /// <summary>
    /// 戦闘ログを追記する
    /// </summary>
    /// <param name="text"></param>
    public static void AddLog(string text)
    {
        logMsg.Add(text);
    }

    /// <summary>
    /// 記録した戦闘ログを取得する
    /// </summary>
    /// <returns></returns>
    public static string GetLog()
    {
        string outMessage = "";

        foreach (string msg in logMsg)
        {
            outMessage += msg + System.Environment.NewLine;
        }

        return outMessage;
    }

    /// <summary>
    /// 戦闘ログをリセットする
    /// </summary>
    public static void Reset()
    {
        logMsg.Clear();
    }

    // 直前の戦闘ログを記録してDBに保持・見返すことができる機能もあっていいか
}
