using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class PartyNode : MonoBehaviour
{
    private Toggle toggle;
    public Text text;
    private Canvas canvasDialog;

    void Start()
    {
        toggle = GetComponent<Toggle>();
        this.canvasDialog = GameObject.Find("CanvasDialog").GetComponent<Canvas>();
        this.canvasDialog.enabled = false;
    }
    public void SwitchToggle()
    {
        int toggleCount = 0;
        Button btn = GameObject.Find("StartButton").GetComponent<Button>();

        foreach (Toggle toggle in Object.FindObjectsOfType(typeof(Toggle)))
        {
            if (toggle.isOn) { toggleCount += 1; }
        }
        if (toggleCount >= 1 && toggleCount <= 3)
        {
            btn.interactable = true;
        }
        else
        {
            this.canvasDialog.enabled = true;

            btn.interactable = false;

        }
        text = GameObject.Find("StartButton").GetComponentInChildren<Text>();
        text.text = $"このパーティーで開始する({toggleCount}/3)";

        Debug.Log("チェックされました。");
    }
}