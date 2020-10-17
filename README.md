# TechReqs
Provides additional requirements for the premium TechTrees plugin found [here](https://www.spigotmc.org/resources/techtree.82209/)

## Usage
#### papistring (PlaceholderAPI String)
Use `papistring` to see if a placeholder's string matches with case/without case

| Line Config Arg. | Description |
| ------------- | ------------- |
| placeholder (String) | The PlaceholderAPI placeholder to parse |
| operator (String) | "equals" or "not", not means the placeholder is anything but match |
| match (String) | The string the parsed placeholder should match |
| case (Boolean) | Whether the placeholder should have to have the same case as match (Default = false) |
| display (String) | Replaces the {display} placeholder in the lore format. |

example: 

`- 'papistring{placeholder="%vault_rank%",operator="equals",match="VIP",case="false",Display="VIP Rank"}'`


### papimath (PlaceholderAPI Math)
Use `papimath` to see if a placeholder is mathematically == != > >= < <= a number

| Line Config Arg. | Description |
| ------------- | ------------- |
| placeholder (String) | The PlaceholderAPI placeholder to parse |
| operator (String) | Valid operators are "==" "!=" ">" ">=" "<" and "<=" |
| match (Double) | The number the placeholder should equal (Ignores trailing 0s so 5.50 would match 5.5, 5.0 matches 5, etc.) |
| display (String) | Replaces the {display} placeholder in the lore format. |

example: 

`- 'papimath{placeholder="%vault_eco_balance%",operator=">=",match=100,Display="$100"}'`

### item (Vanilla Item)
Use `item` to see if a player has the specified amount of vanilla items (currently only works with normal items and custom item names)
DOES NOT SUPPORT ITEMS WITH CUSTOM LORE, ENCHANTS, ETC. TO KEEP FROM TAKING PLAYER'S GEAR

| Line Config Arg. | Description |
| ------------- | ------------- |
| material (String) | The Spigot material of the item: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html |
| amount (Integer) | The required amount of items. (Default = 1) |
| name (String) | The custom Display Name of the item (Skip if no custom name) |
| take (Boolean) | Whether or not the take the required amount when unlocked. (Default = true) |

example: 

`- 'item{material="DIAMOND",amount=10,name="&cRed Diamond Text",take="true"}'`
