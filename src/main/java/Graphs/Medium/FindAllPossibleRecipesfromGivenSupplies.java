package Graphs.Medium;

import java.util.*;

public class FindAllPossibleRecipesfromGivenSupplies {
    HashSet<String> suppliesSet=new HashSet<>();
    HashSet<String> resultSet=new HashSet<>();
    HashSet<String> cantBePreparedSet=new HashSet<>();
    HashMap<String, List<String>> recipeMapIngredients=new HashMap<>();
    HashSet<String> visited;
    /*
     * Approach: DFS
     * When a item is preparable then we add it to resultSet after dfs of that node.
     * If item is not preparable then add it to cantBePreparedSet
     * suppliesSet contains all initially available supplies
     * When we perform a dfs then we check the below:
     * 1. If it there in the suppliesSet or resultSet then it is available.
     * 2. If it is there in cantBePreparedSet or not there in recipeMapIngredients or if there is a cycle then
     *    it is not preparable.
     */
    //!Optimal
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        suppliesSet.addAll(Arrays.asList(supplies));
        for(int i=0;i< recipes.length;i++)
            recipeMapIngredients.put(recipes[i], ingredients.get(i));

        for(int i=0;i< recipes.length;i++){
            visited =new HashSet<>();
            dfs(recipes[i]);
        }
        return new ArrayList<>(resultSet);
    }

    private boolean dfs(String item) {
        if(suppliesSet.contains(item) || resultSet.contains(item))
            return true;
        if(cantBePreparedSet.contains(item) || !recipeMapIngredients.containsKey(item) || visited.contains(item))
            return false;

        visited.add(item);
        boolean result=true;
        for(String neighbour: recipeMapIngredients.get(item)){
            result=result && dfs(neighbour);
        }

        if(!result)
            cantBePreparedSet.add(item);
        else resultSet.add(item);
        return result;
    }

    public static void main(String[] args) {

    }
}
