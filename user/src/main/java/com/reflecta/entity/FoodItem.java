package com.reflecta.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "fooditem") 
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    //private String description;
    private String category;
    
    private double caloriesPerServing;
    private double carbsPerServing;
    private double proteinPerServing;
    private double fatPerServing;
    private double fiberPerServing;
    private double sugarPerServing;
    
    private double servingSize = 1.0;
   

 // --- Constructors ---
    
    public FoodItem( ) {}
    
	
	public FoodItem(Long id, String name, String description, String category, double caloriesPerServing,
			double carbsPerServing, double proteinPerServing, double fatPerServing, double fiberPerServing,
			double sugarPerServing, double servingSize, String servingUnit, String servingDescription) {
		super();
		this.id = id;
		this.name = name;
		//this.description = description;
		this.category = category;
		this.caloriesPerServing = caloriesPerServing;
		this.carbsPerServing = carbsPerServing;
		this.proteinPerServing = proteinPerServing;
		this.fatPerServing = fatPerServing;
		this.fiberPerServing = fiberPerServing;
		this.sugarPerServing = sugarPerServing;
		this.servingSize = servingSize;
		
	}

	// --- toString ---

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", category=" + category
				+ ", caloriesPerServing=" + caloriesPerServing + ", carbsPerServing=" + carbsPerServing
				+ ", proteinPerServing=" + proteinPerServing + ", fatPerServing=" + fatPerServing + ", fiberPerServing="
				+ fiberPerServing + ", sugarPerServing=" + sugarPerServing + ", servingSize=" + servingSize
				+ "]";
	}
	
	// --- Getters & Setters --
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCaloriesPerServing() {
		return caloriesPerServing;
	}
	public void setCaloriesPerServing(double caloriesPerServing) {
		this.caloriesPerServing = caloriesPerServing;
	}
	public double getCarbsPerServing() {
		return carbsPerServing;
	}
	public void setCarbsPerServing(double carbsPerServing) {
		this.carbsPerServing = carbsPerServing;
	}
	public double getProteinPerServing() {
		return proteinPerServing;
	}
	public void setProteinPerServing(double proteinPerServing) {
		this.proteinPerServing = proteinPerServing;
	}
	public double getFatPerServing() {
		return fatPerServing;
	}
	public void setFatPerServing(double fatPerServing) {
		this.fatPerServing = fatPerServing;
	}
	
//	  public String getDescription() {
//	        return description;
//	    }
//	    
//	    public void setDescription(String description) {
//	        this.description = description;
//	    }
	    
	    public String getCategory() {
	        return category;
	    }
	    
	    public void setCategory(String category) {
	        this.category = category;
	    }
	    
	    public double getFiberPerServing() {
	        return fiberPerServing;
	    }
	    
	    public void setFiberPerServing(double fiberPerServing) {
	        this.fiberPerServing = fiberPerServing;
	    }
	    
	    public double getSugarPerServing() {
	        return sugarPerServing;
	    }
	    
	    public void setSugarPerServing(double sugarPerServing) {
	        this.sugarPerServing = sugarPerServing;
	    }
	    
	    public double getServingSize() {
	        return servingSize;
	    }
	    
	    public void setServingSize(double servingSize) {
	        this.servingSize = servingSize;
	    }
	    
	   
}

