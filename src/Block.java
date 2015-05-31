// --------------------------------------------------------
// File:        Block.java
// Authors:     Roberto Rodriguez, Sang Pham, Mike Claros
// Team:        SOF
// Class:       CS 445
//
// Assignment:  Check Point 2
// Date last modified: 5/18/2015
//
// Purpose: Represents a Block to be drawn
// --------------------------------------------------------

public class Block {
    private boolean isActive;
    private BlockType type;
    private float x, y, z;

    // Enum: BlockType
    // Purpose: Represents different types of blocks
    public enum BlockType {
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5);

        private int blockID;

        // Method: BlockType
        // Purpose: Constructor for enum BlockType
        BlockType(int blockID) {
            this.blockID = blockID;
        }

        // Method: getBlockID
        // Purpose: Gets the BlockType's id
        public int getBlockID() {
            return blockID;
        }

        // Method: setBlockID
        // Purpose: Sets the BlockType's id
        public void setBlockID(int blockID) {
            this.blockID = blockID;
        }
    }

    // Method: Block
    // Purpose: Block constructor
    public Block(BlockType type) {
        this.type = type;
        isActive = false;
        x = 0;
        y = 0;
        z = 0;
    }

    // Method: setCoords
    // Purpose: Set the coordinates for the Block
    private void setCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Method: isActive
    // Purpose: Returns boolean if the block is active
    public boolean isActive() {
        return isActive;
    }

    // Method: setActive
    // Purpose: Sets the Block's active status
    public void setActive(boolean active) {
        isActive = active;
    }

    // Method: geTypeID
    // Purpose: Gets the Block's type id
    public int getTypeID() {
        return type.getBlockID();
    }
}
