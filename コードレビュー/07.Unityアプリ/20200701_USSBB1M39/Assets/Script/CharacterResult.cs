using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class CharacterResult : MonoBehaviour
{

    private Text textName;
    private Text textJob;
    private Text textStatus;
    private string name;
    private Canvas canvasDialog;
    private int characterLimit;

    /// <summary>
    /// シーン開始後に呼び出される
    /// </summary>
    void Start()
    {
        Debug.Log("「キャラクター作成完了画面」に遷移した");

        this.canvasDialog = GameObject.Find("CanvasDialog").GetComponent<Canvas>();
        this.canvasDialog.enabled = false;
        this.characterLimit = 10;

        this.name = CharacterList.CharacterName;
        Debug.Log($"name：{this.name}");

        // キャラクターのデータを取得する
        ProcessingSQL countSQL = new ProcessingSQL();
        DataTable dataTable = countSQL.SelectSQL(this.name);

        //変数の準備
        var name = "";
        int job = 0;
        int hp = 0;
        int mp = 0;
        int str = 0;
        int def = 0;
        int agi = 0;
        int luck = 0;
        string create_at = null;

        // DBのデータを変数に格納
        foreach (DataRow dr in dataTable.Rows)
        {
            name = (string)dr["name"];
            job = (int)dr["job"];
            hp = (int)dr["hp"];
            mp = (int)dr["mp"];
            str = (int)dr["str"];
            def = (int)dr["def"];
            agi = (int)dr["agi"];
            luck = (int)dr["luck"];
            create_at = (string)dr["create_at"];

        }

        // 名前の表示用
        textName.text = name;

        //職業の表示用
        if (job == 0)
        {
            textJob.text = "戦士";
        }
        else if (job == 1)
        {
            textJob.text = "魔法使い";
        }
        else if (job == 2)
        {
            textJob.text = "僧侶";
        }
        else
        {
            textJob.text = "勇者";
        }

        textStatus.text = string.Format("\n{0}\n{1}\n{2}\n{3}\n{4}\n{5}", hp, mp, str, def, agi, luck);
    }

    /// <summary>
    /// ボタンクリック時に呼び出される
    /// </summary>
    public void ButtonClick()
    {
        switch (transform.name)
        {
            case "NextCreateButton":
                Debug.Log("「続けて作成する」を押した");

                ProcessingSQL countSQL = new ProcessingSQL();
                int count = countSQL.CountSQL();
                if (count < this.characterLimit)
                {
                    SceneManager.LoadScene("CharacterCreate");
                } else
                {
                    // ダイアログ表示
                    this.canvasDialog.enabled = true;
                }

                break;
            case "CreateEndButton":
                Debug.Log("「作成を終了する」を押した");
                SceneManager.LoadScene("CharacterList");
                break;
            case "BackButton":
                Debug.Log("「戻る」を押した");
                SceneManager.LoadScene("CharacterCreate");
                break;
            case "OKButton":
                this.canvasDialog.enabled = false;
                break;
            default:
                break;
        }

    }
}
