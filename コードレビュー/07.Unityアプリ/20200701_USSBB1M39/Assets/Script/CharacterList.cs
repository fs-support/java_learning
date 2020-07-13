using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class CharacterList : MonoBehaviour
{
    [HideInInspector]public static string CharacterName { set; get; }
    private int characterLimit;


    void Start()
    {
        Debug.Log("キャラクター一覧画面に遷移した");

        this.characterLimit = 10;

        // スクロールビューに表示するノードの数を調べる
        ProcessingSQL countSQL = new ProcessingSQL();
        int count = countSQL.CountSQL();

        Button btn = GameObject.Find("CreateButton").GetComponent<Button>();

        if (this.characterLimit <= count || 0 > count)
        {
            btn.interactable = false;

        } else{
            btn.interactable = true;
        }
    }


    /// <summary>
    /// ボタンクリック時の処理
    /// </summary>
    public void ButtonClick()
    {
        switch (transform.name)
        {
            case "CreateButton":
                Debug.Log("「新しく作成する」を押した");
                SceneManager.LoadScene("CharacterCreate");
                break;
            case "BackButton":
                Debug.Log("「戻る」を押した");
                SceneManager.LoadScene("TopScreen");
                break;
            default:
                break;
        }

    }
}
