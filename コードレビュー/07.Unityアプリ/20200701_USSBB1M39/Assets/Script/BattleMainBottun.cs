using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using System.Linq;

public class BattleMainBottun : MonoBehaviour
{
    private ToggleGroup toggleGroup;
    private Canvas canvasDialog;
    private GameObject BMObject;
    private BattleMain script;

    void Start()
    {
        this.script = GameObject.Find("BattleMain").GetComponent<BattleMain>();
        this.canvasDialog = GameObject.Find("ChangeStrategy").GetComponent<Canvas>();
        this.canvasDialog.enabled = false;
    }


    /// <summary>
    /// バトルメイン画面のボタンを押下した
    /// </summary>
    public void ButtonClick()
    {
        switch (transform.name)
        {
            case "BackButton":
                Debug.Log("「戻る」を押した");
                // 選択された作戦の取得
                string selectedLabel = toggleGroup.ActiveToggles().First().GetComponentsInChildren<Text>()
                    .First(t => t.name == "Label").text;
                Debug.Log(selectedLabel + "が選択された");
                switch (selectedLabel)
                {
                    case "バランスよく":
                        Debug.Log("DefaultStrategyが選択された");

                        this.script.ChangePartyStrategy(new DefaultStrategy(), selectedLabel);

                        break;
                    case "いのちだいじに":
                        Debug.Log("HealStrategyが選択された");
                        
                        this.script.ChangePartyStrategy(new HealStrategy(), selectedLabel);

                        break;
                    case "てきをへらせ":
                        Debug.Log("BulleyStrategyが選択された");

                        this.script.ChangePartyStrategy(new BulleyStrategy(), selectedLabel);

                        break;
                    case "まほうつかうな":
                        Debug.Log("SavingStrategyが選択された");

                        this.script.ChangePartyStrategy(new SavingStrategy(), selectedLabel);

                        break;
                    case "まほうそししろ":
                        Debug.Log("AntiWizardStrategyが選択された");

                        this.script.ChangePartyStrategy(new AntiWizardStrategy(), selectedLabel);
                        
                        break;
                    default:
                        Debug.Log("default");

                        break;
                }
                this.canvasDialog.enabled = false;
                break;
            case "ChangeButton":
                Debug.Log("「変更」を押した");

                this.script.OpenCanvas();
                this.canvasDialog.enabled = true;
                break;
            case "NextTurn":
                Debug.Log("「次のターン」を押した");

                //戦闘が終了しているか確認
                if (script.GetBattleResult() != 0)
                {
                    this.script.Reset();
                    SceneManager.LoadScene("ResultScreen");

                    return;
                }
                script.NextTurn();


                break;

            default:
                break;
        }
    }

}
