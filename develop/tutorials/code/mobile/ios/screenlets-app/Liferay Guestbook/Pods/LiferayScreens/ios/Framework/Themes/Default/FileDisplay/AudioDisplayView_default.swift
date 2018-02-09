/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
import UIKit
import AVFoundation

@objc(AudioDisplayView_default)
open class AudioDisplayView_default: BaseScreenletView, FileDisplayViewModel {

	open var volume: Float = 0.5

	open var numberOfLoops = -1

	// MARK: Outlets

	@IBOutlet weak var view: UIView?

	@IBOutlet weak var playButton: UIButton?

	@IBOutlet weak var pauseButton: UIButton?

	@IBOutlet weak var rewindButton: UIButton?

	@IBOutlet weak var forwardButton: UIButton?

	@IBOutlet weak var sliderDuration: UISlider? {
		didSet {
			sliderDuration?.setThumbImage(
				Bundle.imageInBundles(
					name: "default-point",
					currentClass: type(of: self)),
				for: .normal)
		}
	}

	@IBOutlet weak var sliderVolume: UISlider? {
		didSet {
			sliderVolume?.setThumbImage(
				Bundle.imageInBundles(
					name: "default-point",
					currentClass: type(of: self)),
				for: .normal)
		}
	}

	@IBOutlet weak var audioProgressLabel: UILabel?

	@IBOutlet weak var audioDurationLabel: UILabel?

	@IBOutlet weak var titleLabel: UILabel?

	// MARK: FileDisplayViewModel

	open var url: URL? {
		didSet {
			if let url = url {
				do {
					self.audio = try AVAudioPlayer(contentsOf: url)
					if let audio = audio {
						audio.volume = self.volume
						audio.numberOfLoops = self.numberOfLoops
						playAction()

						self.sliderDuration?.maximumValue = Float(audio.duration)
						self.audioDurationLabel?.text =
							updateAudioDurationLabel(Float(audio.duration))
					}
				}
				catch let error as NSError {
					disableComponents()
					print("AVAudioPlayer error: \(error.debugDescription)")
				}
			}
		}
	}

	open var title: String? {
		didSet {
			self.presentingViewController?.title = title
			self.titleLabel?.text = title
		}
	}

	open var audio: AVAudioPlayer?

	open var timer: Timer?

	open var duration: Timer?

	// MARK: BaseScreenletView

	override open func onHide() {
		audio?.stop()
	}

	// MARK: Actions

	@IBAction func pauseAction() {
		audio?.pause()
		timer?.invalidate()
		duration?.invalidate()
		self.updateView(false)
	}

	@IBAction func playAction() {
		audio?.play()

		timer = Timer.scheduledTimer(timeInterval: 0.01,
				target: self,
				selector: #selector(AudioDisplayView_default.updateProgress),
				userInfo: nil,
				repeats: true)

		duration = Timer.scheduledTimer(timeInterval: 0.01,
				target: self,
				selector: #selector(AudioDisplayView_default.updateDurationLabel),
				userInfo: nil,
				repeats: true)

		self.updateView(true)
	}

	@IBAction func sliderDurationChanged(_ sender: UISlider) {
		changeCurrentTime(sender.value, slider: true)
	}

	@IBAction func sliderVolumeChanged(_ sender: UISlider) {
		let selectedValue = sender.value
		audio?.volume = selectedValue
	}

	@IBAction func moveRewindAction() {
		changeCurrentTime(-5, slider: false)
	}

	@IBAction func moveForwardAction() {
		changeCurrentTime(5, slider: false)
	}

	// MARK: Private methods

	fileprivate func updateView(_ play: Bool) {
		self.pauseButton?.isHidden = !play
		self.playButton?.isHidden = play
	}

	fileprivate func updateAudioDurationLabel(_ interval: Float) -> String {
		let interval = Int(interval)
		let seconds = interval % 60
		let minutes = (interval / 60) % 60
		return String(format: "%02d:%02d", minutes, seconds)
	}

	fileprivate func changeCurrentTime(_ time: Float, slider: Bool) {
		if slider {
			audio?.currentTime = TimeInterval(time)
		}
		else {
			audio?.currentTime += TimeInterval(time)
		}
		updateProgress()
		updateDurationLabel()
	}

	fileprivate func disableComponents() {
		playButton?.isEnabled = false
		rewindButton?.isEnabled = false
		forwardButton?.isEnabled = false
		sliderDuration?.isEnabled = false
		sliderVolume?.isEnabled = false
		pauseButton?.isHidden = true
	}

	// MARK: Internal methods

	func updateProgress() {
		self.sliderDuration?.setValue(Float(audio!.currentTime), animated: true)
	}

	func updateDurationLabel() {
		let currentTime = audio!.currentTime
		if audio!.isPlaying && currentTime == audio!.duration {
			self.updateView(false)
			self.audioProgressLabel?.text = updateAudioDurationLabel(0)
		}
		else {
			self.audioProgressLabel?.text = updateAudioDurationLabel(Float(currentTime))
		}
	}
}
