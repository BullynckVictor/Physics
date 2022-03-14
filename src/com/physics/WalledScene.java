package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;

public class WalledScene extends Scene {

    public WalledScene(Renderer renderer, SceneHandler sceneHandler)
    {
        super(renderer, sceneHandler);
        Debug.setRenderer(renderer);
        Debug.setColor(Color.LIGHT_GRAY);
    }

    @Override
    public void load() {
        super.load();
        engine.forceCalculator = new GravityCalculator();

        Upwall = new PhysicsObject(new AAB(3,5f));
        Downwall = new PhysicsObject(new AAB(3,5f));
        Leftwall = new PhysicsObject(new AAB(5f,1.9f));
        Rightwall = new PhysicsObject(new AAB(5f,1.9f));

        Upwall.position.set(0,3.5f);
        Downwall.position.set(0,-3.5f);
        Leftwall.position.set(-4f,0);
        Rightwall.position.set(4f, 0);

        Upwall.movable = false;
        Downwall.movable = false;
        Leftwall.movable = false;
        Rightwall.movable = false;

        Ball1 = new PhysicsObject(new Circle(.1f));
        Ball2 = new PhysicsObject(new Circle(.1f));

        Ball1.position.set(0.1f,0.5f);
        Ball2.position.set(0,0);

        engine.addObject(Upwall);
        engine.addObject(Downwall);
        engine.addObject(Leftwall);
        engine.addObject(Rightwall);

        engine.addObject(Ball1);
        engine.addObject(Ball2);

    }
    @Override
    public void unload()
    {
        super.unload();
    }

    @Override
    public void update(DeltaTime dt) throws Exception
    {
        engine.compute(dt);
        controlCamera(dt.seconds());
        updateScene();
    }

    @Override
    public void render()
    {
        renderer.drawObject(Downwall, Color.black);
        renderer.drawObject(Upwall, Color.black);
        renderer.drawObject(Leftwall, Color.black);
        renderer.drawObject(Rightwall, Color.black);

        renderer.drawObject(Ball1, Color.green);
        renderer.drawObject(Ball2, Color.red);


    }
    private PhysicsObject Downwall;
    private PhysicsObject Upwall;
    private PhysicsObject Leftwall;
    private PhysicsObject Rightwall;
    private PhysicsObject Ball1;
    private PhysicsObject Ball2;

}
