/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class Category {
    private int cateID;
    private String cateName;
    private String cateDescription;

    public Category(int cateID, String cateName, String cateDescription) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.cateDescription = cateDescription;
    }

    public Category() {
    }
    
    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateDescription() {
        return cateDescription;
    }

    public void setCateDescription(String cateDescription) {
        this.cateDescription = cateDescription;
    }
    
}
