// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modeljeanarrow extends EntityModel<Entity> {
	private final ModelRenderer arrow;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;

	public Modeljeanarrow() {
		textureWidth = 32;
		textureHeight = 32;

		arrow = new ModelRenderer(this);
		arrow.setRotationPoint(0.0F, 1.775F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arrow.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.7854F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 6).addBox(-7.0F, -2.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, 0.0F, 16.0F, 5.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		arrow.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.7854F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-8.0F, -2.5F, 0.5F, 16.0F, 5.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		arrow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}