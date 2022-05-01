package com.physics;

import com.physics.util.DeltaTime;

import java.awt.*;
import java.awt.event.KeyEvent;

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
        Ball3 = new PhysicsObject(new Circle(.1f));

        do {
            Ball1.position = Vector.random();
            Ball2.position = Vector.random();
        }
        while(Vector.distanceSQ(Ball1.position, Ball2.position) <= (.1 + .1) * (.1 + .1));

        Ball1.velocity = Vector.normalise(Vector.sub(Ball2.position, Ball1.position));
        Ball2.velocity = Vector.normalise(Vector.sub(Ball1.position, Ball2.position));
        Ball3.position.set(-1.1f, 0);

        engine.addObject(Upwall);
        engine.addObject(Downwall);
        engine.addObject(Leftwall);
        engine.addObject(Rightwall);

        engine.addObject(Ball1);
        engine.addObject(Ball2);
        engine.addObject(Ball3);
    }
    @Override
    public void unload()
    {
        super.unload();
    }

    @Override
    public void update(DeltaTime dt) throws Exception
    {
        if (input.keyboard.keyFlagged(KeyEvent.VK_SPACE) != 0)
        {
            Ball1.velocity.add(Vector.normalise(Vector.sub(Ball2.position, Ball1.position)));
            Ball2.velocity.add(Vector.normalise(Vector.sub(Ball1.position, Ball2.position)));
        }

        engine.compute(dt);
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
        renderer.drawObject(Ball3, Color.blue);


    }
    private PhysicsObject Downwall;
    private PhysicsObject Upwall;
    private PhysicsObject Leftwall;
    private PhysicsObject Rightwall;
    private PhysicsObject Ball1;
    private PhysicsObject Ball2;
    private PhysicsObject Ball3;

}
