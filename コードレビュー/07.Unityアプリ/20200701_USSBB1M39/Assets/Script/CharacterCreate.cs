using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System.Linq;
using UnityEngine.UI;
using System;


public class CharacterCreate : MonoBehaviour
{
    public InputField inputField;
    public ToggleGroup toggleGroup;


    public void ButtonClick()
    {
        switch (transform.name)
        {
            case "CreateButton":
                Debug.Log("「作成する」を押した");

                string name = "";

                //  入力されたテキストから名前を取得
                this.inputField = inputField.GetComponent<InputField>();
                name = inputField.text;

                // 選択された職業の取得
                string selectedLabel = toggleGroup.ActiveToggles().First().GetComponentsInChildren<Text>()
                    .First(t => t.name == "Label").text;
                Debug.Log(selectedLabel + "が選択された");

                // キャラクターの追加
                ProcessingSQL countSQL = new ProcessingSQL();
                countSQL.InsertSQL(name, selectedLabel);

                // 
                CharacterList.CharacterName = name;
                Debug.Log(CharacterList.CharacterName);

                Debug.Log("キャラクター作成終了");
                // キャラクター作成完了画面に遷移
                SceneManager.LoadScene("CreateResult");
                break;

            case "BackButton":
                Debug.Log("「戻る」を押した");
                SceneManager.LoadScene("CharacterList");
                break;
            default:
                break;
        }

    }
}
